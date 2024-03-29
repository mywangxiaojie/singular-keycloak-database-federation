# singular-keycloak-database-federation



### Compatible with Keycloak 17+ quarkus based.

### ** Keycloak 19+ ** KNOWN ISSUE:

#### New Theme breaks custom providers, to overcome this problem, follow these steps:


 - Click "Realm Settings" on the left menu
 - Then click the tab "Themes"
 - And, for the selection input labeled "Admin console theme", select "keycloak"
 - Logoff and login again
 - Now, if you try to configure this provider again, keycloak should render all configuration fields and everything else should work fine.
 
 See issue #19 for further information.



**For older versions look at older_versions branch.


Keycloak User Storage SPI for Relational Databases (Keycloak User Federation, supports postgresql, mysql, oracle and mysql).

- Keycloak User federation provider with SQL
- Keycloak User federation using existing database
- Keycloak  database user provider
- Keycloak MSSQL Database Integration 
- Keycloak SQL Server Database Integration 
- Keycloak Oracle Database Integration 
- Keycloak Postgres Database Integration 
- Keycloak blowfish bcrypt support



## Usage

    Fully compatible with Singular Studio NOCODE. See https://www.studio.opensingular.com/
    

## Configuration

Keycloak User Federation Screen Shot

![Sample Screenshot](screen.png)

There is a new configuration that allows keycloak to remove a user entry from its local database (this option has no effect on the source database). It can be useful when you need to reload user data.
This option can be configured by the following switch:

![Sample Screenshot](deleteuser.png)

## Limitations

    - Do not allow user information update, including password update
    - Do not supports user roles our groups

## Custom attributes

Just add a mapper to client mappers with the same name as the returned column alias in your queries.Use mapper type "User Attribute". See the example below:
    
![Sample Screenshot 2](screen2.png)


## Build

    - mvn clean package

## Deployment

    1) Copy every  `.jar` from dist/ folder  to  /providers folder under your keycloak installation root. 
        - i.e, on a default keycloak setup, copy all  `.jar` files to <keycloak_root_dir>/providers
    2) run :
        $ ./bin/kc.sh start-dev
    OR if you are using a production configuration:
        $ ./bin/kc.sh build
        $ ./bin/kc.sh start

## For futher information see:
    - https://github.com/keycloak/keycloak/issues/9833
    - https://www.keycloak.org/docs/latest/server_development/#packaging-and-deployment
    
    
## Keycloak 17+ (e.g. quay.io/keycloak/keycloak:17.0.0) doesn't support autogeneration of selfsigned cert. Minimal HTTPS working example for Keycloak 17+:  
    
1.) Generate selfsigned domain cert/key (follow instructions on your terminal):
```
openssl req -newkey rsa:2048 -nodes \
  -keyout server.key.pem -x509 -days 3650 -out server.crt.pem
```
2.) Update permissions for the key
```
chmod 755 server.key.pem
```
3.) 重命名证书
```aidl
mv keycloak.pem  tls.key
mv keycloak.crt tls.crt
```
4.)说明
1. keycloak的ssl默认有自己的自签名证书，这个如果涉及到你的程序调用kc的接口，kc使用自定义证书是不行的，你调不通，使用使用正规的证书
2. ssl的https端口是8443，在使用docker启动时，监听它即可
3. ssl的自定义证书目录是/etc/x509/https,使用docker时，把自定义证书挂载到这个目录即可

为什么要使用https

