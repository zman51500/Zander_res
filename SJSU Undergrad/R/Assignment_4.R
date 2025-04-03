#1
plotMaker <- function(x){
  n = length(x)
  if(n > 5){
    return("Too many variables")
  }
  else{
    pdf("Q1.pdf")
    par(mfrow = c(n,2))
    for(i in 1:n){
      hist(x[,i], col = i+1, xlab = NULL, main = names(x[i]))
      boxplot(x[,i], col = i+1)
    }
    dev.off()
  }
}
library(MASS)
plotMaker(Cars93[c(5,7)])

#2
test = matrix(c(1,2,3,4,5,6), nrow = 2)
sampCoef <- function(x){
  sum = apply(x,2,sum)
  len = apply(x,2,length)
  mean = sum/len
  dif = sweep(x, MARGIN = 2, mean, FUN = "-")
  difSqr = dif^2
  sumDif = apply(difSqr,2, sum)
  difDiv = sumDif/(len-1)
  return(sqrt(difDiv)/ mean)
}
sampCoef(test)

test2 = c(1,1,1,1,1,1,1,1)
#3
noDup <- function(x){
  done = F
  i = 1
  while(!done){
    len = length(x)
    if(i >= len){
      done = T
    }
    else{
      for(j in (i + 1):(len)){
        logic = NULL
        if(x[i] %% x[j] == 0){
          if(x[i] == x[j]){
            logic[j] = F
            break
          }
          logic[j] = T
        }
        logic[j] = T
      }
      x = x[logic]
      i = i + 1
    }
  }
  return(x)
}
noDup(test2)
