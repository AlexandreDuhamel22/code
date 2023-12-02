# Principes et Méthodes Statistiques
# Fiche 4 - Exercice 1

# Création du vecteur des données des ampoules

ampoules<-c(91.6, 35.7,251.3, 24.3, 5.4, 67.3, 170.9, 9.5, 118.4, 57.1)
n<-length(ampoules)

# EMV et EMM de lambda

lambdaMV<-1/mean(ampoules)
lambdaMV

# ESBVM de lambda

lambdaest<-(n-1)/sum(ampoules)
lambdaest

# Intervalle de confiance bilatéral de seuil alpha pour lambda

alpha<-0.5
qchisq(alpha/2,2*n)/(2*sum(ampoules))
qchisq(1-alpha/2,2*n)/(2*sum(ampoules))

alpha<-0.1
qchisq(alpha/2,2*n)/(2*sum(ampoules))
qchisq(1-alpha/2,2*n)/(2*sum(ampoules))

alpha<-0.05
qchisq(alpha/2,2*n)/(2*sum(ampoules))
qchisq(1-alpha/2,2*n)/(2*sum(ampoules))

alpha<-0.01
qchisq(alpha/2,2*n)/(2*sum(ampoules))
qchisq(1-alpha/2,2*n)/(2*sum(ampoules))


# Vérification du taux de couverture de l'IC bilatéral

IntConfExp<-function(m,n,alpha,lambda)
{
prop<-0
for (i in 1:m)
{
ech<-rexp(n,lambda)
if ((qchisq(alpha/2,2*n)/(2*sum(ech)) < lambda)
 & (lambda < qchisq(1-alpha/2,2*n)/(2*sum(ech)))) prop<-prop+1
}
cat("Proportion d'intervalles contenant le paramétre", prop/m,?"\n")
}

IntConfExp(1000,50,0.5,10)
IntConfExp(1000,50,0.1,10)
IntConfExp(1000,50,0.05,10)
IntConfExp(1000,50,0.01,10)


# Estimation du biais des estimateurs

BiaisExp<-function(m,n,lambda)
{
EMV<-rep(0,m)
ESBVM<-rep(0,m)
for (i in 1:m)
{
x<-rexp(n,lambda)
EMV[i]<- 1/mean(x)
ESBVM[i]<- (n-1)*EMV[i]/n
}
par(mfcol=c(1,2))
hist(EMV,prob=T)
lines(c(lambda,lambda),c(0,10),col="blue",lwd=4)
lines(c(mean(EMV),mean(EMV)),c(0,10),col="red",lwd=4)
hist(ESBVM,prob=T)
lines(c(lambda,lambda),c(0,10),col="blue",lwd=4)
lines(c(mean(ESBVM),mean(ESBVM)),c(0,10),col="red",lwd=4)
cat("Moyenne des biais de l'EMV", mean(EMV-lambda),"\n")
cat("Moyenne des biais de l'ESBVM", mean(ESBVM-lambda),"\n")
}

BiaisExp(100,5,10)
BiaisExp(100,10,10)
BiaisExp(100,20,10)
BiaisExp(100,50,10)
BiaisExp(100,100,10)
BiaisExp(100,500,10)


# Estimation de l'erreur quadratique moyenne des estimateurs

EQMExp<-function(m,n,lambda)
{
EMV<-rep(0,m)
ESBVM<-rep(0,m)
for (i in 1:m)
{
x<-rexp(n,lambda)
EMV[i]<- 1/mean(x)
ESBVM[i]<- (n-1)*EMV[i]/n
}
cat("Moyenne des EQM de l'EMV", mean((EMV-lambda)^2),"\n")
cat("Moyenne des EQM de l'ESBVM", mean((ESBVM-lambda)^2),"\n")
}

EQMExp(100,5,10)
EQMExp(100,10,10)
EQMExp(100,20,10)
EQMExp(100,50,10)
EQMExp(100,100,10)
EQMExp(100,500,10)



