## Docker commands

1. docker --version
2. docker image ls
3. docker container ls
4. docker ps
5. docker ps -a
6. docker build -t mhazim95/ssf-day18:v1.0 .
7. docker run -d -p 8080:1234 mhazim95/ssf-day18:v1.0
8. docker stop <first 4 char of container ID>
9. docker rm <first 4 char of container ID>
10. docker rmi <first 4 char of image ID>
11. docker system prune (to remove docker compile cache before recompiling)


## Railway
In project root:
1. railway login 
2. railway link
3. railway up