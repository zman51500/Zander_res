#1 for loop
prodFor <- function(x){
  if(is.numeric(x) | is.integer(x)){
    prod = 1
    for(i in 1:length(x)){
      prod = x[i] * prod
    }
    return(prod)
  }
  return(cat("invalid entry:", x))
}
prodFor(c(1,2,3))

#1 while loop
prodWhile <- function(x){
  if(is.numeric(x) | is.integer(x)){
    prod = 1
    index = 1
    while(!is.na(x[index])){
        prod = x[index] * prod
        index = index + 1
    }
    return(prod)
  }
  return(cat("invalid entry:", x))
}
prodWhile(c(1,2,3))

#1 repeat loop
prodRep <- function(x){
  if(is.numeric(x) | is.integer(x)){
    prod = 1
    index = 1
    repeat{
      prod = x[index] * prod
      index = index + 1
      if(is.na(x[index])){
        return(prod)
      }
    }
  }
  return(cat("invalid entry:", x))
}
prodRep(c(1,2,3))

matrixTest1 = matrix(1:6, ncol = 3, nrow = 2, byrow = TRUE)
matrixTest2 = matrix(1:6, ncol = 2, nrow = 3, byrow = TRUE)

#2 for loop
matrixMulFor <- function(x,y){
  if(is.matrix(x) & is.matrix(y)){
    if(nrow(x) == ncol(y)){
      fin = matrix(nrow = nrow(x), ncol = ncol(y))
      for(row in 1:nrow(x)){
        val = NULL
        for(col in 1:ncol(y)){
          val = (x[row,]*y[,col])
          sum = 0
          for(i in 1:length(val)){
            sum = sum + val[i]
          }
          fin[row,col] = sum
        }
      }
      return(fin)
    }
    return("invalid dimensions")
  }
  return(cat("invalid entry x:", x, "y:", y))
}
matrixMulFor(matrixTest1,matrixTest2)
matrixMulFor(matrixTest2,matrixTest1)
matrixMulFor(matrixTest1,matrixTest1)

#2 while loop
matrixMulWhile<- function(x,y){
  if(is.matrix(x) & is.matrix(y)){
    if(nrow(x) == ncol(y)){
      fin = matrix(nrow = nrow(x), ncol = ncol(y))
      row = 1
      rowLim = nrow(x)
      while(row<=rowLim){
        val = NULL
        col = 1
        colLim = ncol(y)
        while(col <= colLim){
          val = (x[row,]*y[,col])
          sum = 0
          index = 1
          valLim = length(val)
          while(index<= valLim){
            sum = sum +val[index]
            index = index + 1
          }
          fin[row,col] = sum
          col = col + 1
        }
        row = row + 1
      }
      return(fin)
    }
    return("invalid dimensions")
  }
  return(cat("invalid entry x:", x, "y:", y))
}
matrixMulWhile(matrixTest1,matrixTest2)
matrixMulWhile(matrixTest2,matrixTest1)
matrixMulWhile(matrixTest1,matrixTest1)

#2 repeat loop
matrixMulRep <- function(x,y){
  if(is.matrix(x) & is.matrix(y)){
    if(nrow(x) == ncol(y)){
      fin = matrix(nrow = nrow(x), ncol = ncol(y))
      row = 1
      rowLim = nrow(x)
      repeat{
        val = NULL
        col = 1
        colLim = ncol(y)
        repeat{
          val = (x[row,]*y[,col])
          sum = 0
          index = 1
          valLim = length(val)
          repeat{
            sum = sum +val[index]
            index = index + 1
            if(index > valLim){
              break
            }
          }
          fin[row,col] = sum
          col = col + 1
          if(col>colLim){
            break
          }
        }
        row = row + 1
        if(row>rowLim){
          break
        }
      }
      return(fin)
    }
    return("invalid dimensions")
  }
  return(cat("invalid entry x:", x, "y:", y))
}
matrixMulRep(matrixTest1,matrixTest2)
matrixMulRep(matrixTest2,matrixTest1)
matrixMulRep(matrixTest1,matrixTest1)
#3 for loop
findXFor <- function(x,y){
  if((is.numeric(x) & length(x) == 1) & (is.numeric(y) | is.integer(y))){
    for(i in 1:length(y)){
      if(y[i]==x){
        return(cat(x, "is in position", i))
      }
    }
    return(cat(x, "is not in the vector", y))
  }
  return(cat("invalid entry x:" , x, "y:", y))
}
findXFor(3,1:5)
findXFor(3,3:5)
findXFor(3,1:3)

#3 while loop
findXWhile <- function(x,y){
  if((is.numeric(x) & length(x) == 1) & (is.numeric(y) | is.integer(y))){
    index = 1
    found = FALSE
    while(!found){
      if(!is.na(y[index])){
        if(y[index] == x){
          return(cat(x, "is in position", index))
          found = TRUE
        }
        index = index + 1
      }
      else{
        return(cat(x, "is not in the vector", y))
      }
    }
  }
  return(cat("invalid entry x:" , x, "y:", y))
} 
findXWhile(3,1:5)
findXWhile(3,3:5)
findXWhile(3,1:3)

#3 repeat loop
findXRep <- function(x,y){
  if((is.numeric(x) & length(x) == 1) & (is.numeric(y) | is.integer(y))){
    index = 1
    repeat{
      if(is.na(y[index])){
        return(cat(x, "is not in the vector", y))
      }
      else if(y[index] == x){
        return(cat(x, "is in position", index))
      }
      index = index + 1
    }
  }
  return(cat("invalid entry x:" , x, "y:", y))
}
findXRep(3,1:5)
findXRep(3,3:5)
findXRep(3,1:3)

#4 for loop
firstNatFor <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x < 0){
      return(1)
    }
    else{
      sum = 0
      list = NULL
      for(i in 1:x){
        if(sum <= x){
          sum = sum + i
          list[i] = i
        }
        else{
          break
        }
      }
      return(list)
    }
  }
  return(cat("invalid entry:", x))
}
firstNatFor(10)
firstNatFor(-1)

#4 while loop
firstNatWhile <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x < 0){
      return(1)
    }
    else{
      i = 1
      sum = 0
      list = NULL
      while(sum <= x){
        sum = sum + i
        list[i] = i
        i = i + 1
      }
      return(list)
    }
  }
  return(cat("invalid entry:", x))
}
firstNatWhile(10)
firstNatWhile(-1)

#4 repeat loop
firstNatRep <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x<0){
      return(1)
    }
    else{
      i = 1
      sum = 0
      list = NULL
      repeat{
        sum = sum + i
        list[i] = i
        i = i +1
        if(sum > x){
          return(list)
        }
      }
    }
  }
  return(cat("invalid entry:", x))
}
firstNatRep(10)
firstNatRep(-1)

#5 for loop
isPrimeFor <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x == 2){
      return(cat(x, "is Prime"))
    }
    else if(x<2){
      return(cat(x, "is Not Prime"))
    }
    else{
      for(i in 2:(x-1)){
        if(x%%i == 0){
          return(cat(x, "is Not Prime"))
        }
      }
      return(cat(x, "is Prime"))
    }
  }
  return(cat("invalid entry:", x))
}
isPrimeFor(2)
isPrimeFor(10)
isPrimeFor(11)

#5 while loop
isPrimeWhile <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x == 2){
      return(cat(x, "is Prime"))
    }
    else if(x<2){
      return(cat(x, "is Not Prime"))
    }
    else{
      i = 2
      limit = (x-1)
      while(i < limit){
        if(x%%i == 0){
          return(cat(x, "is Not Prime"))
        }
        i = i+1
      }
      return(cat(x, "is Prime"))
    }
  }
  return(cat("invalid entry:", x))
}
isPrimeWhile(2)
isPrimeWhile(10)
isPrimeWhile(11)

#5 repeat loop
isPrimeRep <- function(x){
  if(is.numeric(x) & length(x) == 1){
    if(x == 2){
      return(cat(x,"is Prime"))
    }
    else if(x<2){
      return(cat(x,"is Not Prime"))
    }
    else{
      i = 2
      limit = (x-1)
      repeat{
        if(x%%i == 0){
          return(cat(x,"is Not Prime"))
        }
        i = i + 1
        if(i > limit){
          return(cat(x,"is Prime"))         
        }
      }
    }
  }
  return(cat("invalid entry:", x))
}
isPrimeRep(2)
isPrimeRep(10)
isPrimeRep(11)
