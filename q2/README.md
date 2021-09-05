# Question 2

## get-page.sh

`get-page.sh` is an utility script which will be used to make telnet work

The issue with telnet is that it is not a single command and it takes time
to establish the connection. So the sleep commands needed to be used to do this

I have used [json-server](https://www.npmjs.com/package/json-server) as the
server to get the content from.

## status-check.sh

`status-check.sh` file checks the endpoint status code by sending a request and
used cut and sed commands to get the status code type by getting the first number
of the status code which tells us what is the general type of the error

### Usage

the command has 3 arguments

1. `<endpoint>` - the domain name or IP of the server
2. `<port_number>` - the port number on which the server app is running
3. `<route>` - the route of the webpage which we want to access

### Example

```sh
./status-check.sh google.com 80 /

./status-check.sh localhost 3000 /thispathmayormaynotexists
```

## compare-page.sh

`compage-page.sh` file checks the server document with the content present
inside the testing.out file in the directory and tells if the test is ok 
meaning that the content is same and test is failed meaning that the content
is different

### Usage

the command has 3 arguments

1. `<endpoint>` - the domain name or IP of the server
2. `<port_number>` - the port number on which the server app is running
3. `<route>` - the route of the webpage which we want to access

### Example

```sh
./compare-page.sh google.com 80 /

./compare-page.sh localhost 3000 /thispathmayormaynotexists
```
