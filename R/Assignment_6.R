#1
covCor <- function(x){
  covar = matrix(NA, nrow = ncol(x), ncol = ncol(x))
  corr = matrix(NA, nrow = ncol(x), ncol = ncol(x))
  for(i in 1:ncol(x)){
    for(j in 1:ncol(x)){
      covar[j,i] = stdDev(x[,i], x[,j])
      corr[j,i] = covar[j,i]/sqrt((stdDev(x[,i], x[,i])*stdDev(x[,j],x[,j])))
    }
  }
  return(list(covar, corr))
}
x = matrix(c(1,5,3,8,12,9), nrow = 2)
covCor(x)

stdDev <- function(x,y){
  averX = sum(x)/length(x)
  averY = sum(y)/length(y)
  sumProdDif = sum((x - averX) * (y - averY))
  return(sumProdDif/(length(x)-1))
}

#2
library(datasets)
t.test(beaver1$temp, beaver2$temp, alternative = "two.sided")
#Not paired because we can assume the two beavers are independent
#We can conclude that the average temperature of the two beavers is different
(beav1Var = var(beaver1$temp))
(beav2Var = var(beaver2$temp))

if(beav1Var >= beav2Var){
  (fStat = beav1Var/beav2Var)
  (upperRejcet = qf(.975, length(beaver1$temp)-1, length(beaver2$temp) -1))
  (lowerReject = qf(.025,length(beaver1$temp)-1, length(beaver2$temp) -1))
  (fAlpha = pf(fStat, length(beaver1$temp), length(beaver2$temp)))
}
if(beav1Var < beav2Var){
  (fStat = beav2Var/beav1Var)
  (upperRejcet = qf(.975, length(beaver2$temp)-1, length(beaver1$temp) -1))
  (lowerReject = qf(.025,length(beaver2$temp)-1, length(beaver1$temp) -1))
  (fAlpha = pf(fStat, length(beaver2$temp), length(beaver1$temp), lower.tail = F)*2)
}
#The two variances are statistically different

#3
load("chocolatechips.rdata")
table(chocolatechips)
av = ((1*1)+(2*2)+(3*7)+(4*3)+(5*5)+(6*4)+(7*4)+(8*2)+(9*2))/length(chocolatechips)
expected = NULL
for(i in 1:9){
  expected[i] = (((exp(-av))*av^i)/factorial(i))*length(chocolatechips)

}
chisqr = ((((1-1.037983)^2) /1.037983) + (((2-2.577658)^2) /2.577658)
          + (((7-4.267457)^2) /4.267457) + (((3-5.298759)^2) /5.298759)
          + (((5-5.263434)^2) /5.263434) + (((4-4.356953)^2) /4.356953)
          + (((4-3.091362)^2) /3.091362) + (((2-1.919221)^2) /1.919221)
          + (((2-1.059125)^2) /1.059125))
df = length(chocolatechips) - 2
pchisq(chisqr, df)
#There is evidence to show that chocolatechips does not follow the 
#poisson distribution


