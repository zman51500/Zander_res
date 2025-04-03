#1
(ranNorm1 <- rnorm(50))

#2
(ranNorm2 <- rnorm(50, mean = 32, sd = 16))

#3
ranNormList <- list(ranNorm1,ranNorm2)
lapply(ranNormList, median)
lapply(ranNormList, sum)

#4
ranNormList <- list(ranNorm1,ranNorm2)
lapply(ranNormList, max)
lapply(ranNormList, min)

#5
#Using the logical vector y as an index for x returns
#the values for all indexes that are True in vector y

#6
#You can not find the mean of "y" because it is of the class
#Character and you can not find the mean of class character
#resulting in the NA because there is no data available

#7a
name <- scan("Ass2.txt", what = "a")
username <- strsplit(name, ", ")
test <- list(paste(username[[1]][1], username[[1]][2], sep = "."))
test <- rbind(test,paste(username[[2]][1], username[[2]][2], sep = "."))
test <- rbind(test,paste(username[[3]][1], username[[3]][2], sep = "."))
test <- rbind(test,paste(username[[4]][1], username[[4]][2], sep = "."))
test <- rbind(test,paste(username[[5]][1], username[[5]][2], sep = "."))
test <- rbind(test,paste(username[[6]][1], username[[6]][2], sep = "."))
test <- rbind(test,paste(username[[7]][1], username[[7]][2], sep = "."))
test <- rbind(test,paste(username[[8]][1], username[[8]][2], sep = "."))
test <- rbind(test,paste(username[[9]][1], username[[9]][2], sep = "."))
test <- rbind(test,paste(username[[10]][1], username[[10]][2], sep = "."))
test <- rbind(test,paste(username[[11]][1], username[[11]][2], sep = "."))
test <- rbind(test,paste(username[[12]][1], username[[12]][2], sep = "."))
test <- rbind(test,paste(username[[13]][1], username[[13]][2], sep = "."))
test <- rbind(test,paste(username[[14]][1], username[[14]][2], sep = "."))
test <- rbind(test,paste(username[[15]][1], username[[15]][2], sep = "."))
test <- rbind(test,paste(username[[16]][1], username[[16]][2], sep = "."))
test <- rbind(test,paste(username[[17]][1], username[[17]][2], sep = "."))
test <- rbind(test,paste(username[[18]][1], username[[18]][2], sep = "."))
test <- tolower(test)
domain <-"@IloveR.edu"
(email <- paste(test,domain, sep = ""))

#7b
groups <- c("A","A","A","B","B","B","C","C","C","D","D","D","E","E","E","F","F","F")
groups <- sample(groups)
(Studgrps = data.frame(name = name, email = email, group = groups))

#7c
save(Studgrps, file = "Studgrps.RData")
