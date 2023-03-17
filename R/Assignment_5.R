#1
#The graph shows that the mystery distribution is not a normal distribution
#this is because the points do not follow the 45 degree line
#since they do not follow this line we know that the mystery distribution is not 
#normal

#2
library(ggplot2)
vsT <- function(x,dg){
  ppts <- seq(.01,.99, by = .02)
  tDi <- qt(ppts, df = dg)
  qq <- ggplot(data = data.frame(x,tDi),aes(x = x, y = tDi))
  (qq + geom_point() + geom_abline() + ggtitle("Mystery Data vs. Student-T")
        + xlab("Mystery Distrobution") + ylab("Student-T Distrobution"))
}
test = seq(-3,3, length.out = 50)
vsT(test, 10)
test = qt(seq(.01,.99, by = .02), df = 10)
vsT(test, 10)

#3
ppts <- seq(.01,.99, by = .02)
binom <- qbinom(ppts, 1, .01)
pois <- qpois(ppts,1)
qq1 <- ggplot(data = data.frame(binom, pois), aes(x = binom, y= pois))
qq1 + geom_point() + geom_abline()
#Not even close
binom <- qbinom(ppts, 10, .01)
pois <- qpois(ppts,1)
qq2 <- ggplot(data = data.frame(binom, pois), aes(x = binom, y= pois))
qq2 + geom_point() + geom_abline()
#Closer still not close though
binom <- qbinom(ppts, 1000, .01)
pois <- qpois(ppts,10)
qq3 <- ggplot(data = data.frame(binom, pois), aes(x = binom, y= pois))
qq3 + geom_point() + geom_abline()
#Very close
binom <- qbinom(ppts, 10000, .01)
pois <- qpois(ppts,100)
qq4 <- ggplot(data = data.frame(binom, pois), aes(x = binom, y= pois))
qq4 + geom_point() + geom_abline()
#Nearly the same

#As "n" increases the two distributions converge

#4
hondas <- read_excel("hondas.xlsx")
pl <- ggplot(hondas, aes(x = Year, y = price, color = Miles))
pl + geom_point() + facet_wrap(~type)

#The newer the car the more expensive it tends to be
#The less miles the car has the higher the value tends to be
#The EX model seems to be more expensive than the LX model

