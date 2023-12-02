# Principes et Méthodes Statistiques
# Fiche 5 - Exercice 3

# Création des données

k<-4
n<-1775
nj<-c(458,670,469,178)
pj<-c(0.34,0.32,0.25,0.09)
sum(nj)
sum(pj)
n*pj
nj/n

# Test du chi 2 au seuil 5%

sum((nj-n*pj)^2/(n*pj))
qchisq(0.95,k-1)

# Test du chi 2 avec calcul de la p-valeur

chisq.test(nj,p=pj)

# Test de proportion pour le groupe A

t<-670
p_0<-0.32
# Première possibilité : test asymptotique
stat<-(t-n*p_0)/sqrt(n*p_0*(1-p_0))
stat
1-pnorm(stat)
# Deuxième possibilité : test exact
binom.test(t,n,p=p_0,alternative="greater")

# Test de proportion pour le groupe O

t<-458
p_1<-0.34
# Première possibilité : test asymptotique
stat<-(t-n*p_1)/sqrt(n*p_1*(1-p_1))
stat
pnorm(stat)
# Deuxième possibilité : test exact
binom.test(t,n,p=p_1,alternative="less")
