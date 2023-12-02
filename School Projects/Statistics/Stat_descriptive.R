#Chapitre 2 - Statistique descriptive
#####################################

# Variables discrètes qualitatives
x<-c(2.5, 6.3, 3.3, 6.2, 13.5, 22.4, 2.5, 8.5, 3.5, 23.3, 2.2, 5.8)
partis<-c("PC","LFI","Gen","EEES","EE","LREM","UDI","UDC","DLF","RN","PA","Autres")
barplot(x,names=partis)
pie(x,labels=partis)

# Variables discrètes quantitatives
barplot(c(235,183,285,139,88,67,3),names=c(0,1,2,3,4,5,6))

# Variables continues

# Durées de vie d'ampoules
x<-c(91.6, 35.7, 251.3, 24.3, 5.4, 67.3, 170.9, 9.5, 118.4, 57.1)
x[1]
y<-sort(x)
y
y[1]

# Histogramme à classes de même largeur
seq(0,260,52)
hist(x, prob=T, breaks=seq(0,260,52))

# Histogramme à classes de même effectif
hist(x, prob=T, breaks=c(0,17,46,79,145,260))

# Fonction de répartition empirique
plot(ecdf(x))

# Graphe de probabilités pour la loi exponentielle
seq(1:9)
abs<-y[1:9]
ord<-log(1-seq(1:9)/10)
abs
ord
plot(abs,ord,ylim=c(-2.5,0.1))
abline(v=0)
abline(h=0)
reg<-lm(ord~abs)
reg$coefficient[1]
reg$coefficient[2]
abline(reg)

# Graphe de probabilités pour la loi normale
abs<-y[1:9]
ord<-qnorm(seq(1:9)/10)
ord
plot(abs,ord)
abline(h=0)
reg<-lm(ord~abs)
reg$coefficient[1]
reg$coefficient[2]
abline(reg)

# Indicateurs statistiques
mean(x)
min(x)
max(x)
(min(x)+max(x))/2

y1<-c(1,3,5,8,10)
y2<-c(1,3,5,8,10000)
mean(y1)
mean(y2)
median(y1)
median(y2)

median(x)
var(x)
sd(x)
sd(x)/mean(x)
sqrt(9/10)*sd(x)/mean(x)

quantile(x,1/4)
quantile(x,3/4)
summary(x)

