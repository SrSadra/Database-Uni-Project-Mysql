# LINKEDIN Simulator Using Java & Mysql as Database

It was tasked to simulate linkedin without using any orm just Writing sql queries.This Project has main linkedin features such as
login and signup, creating post, find user and send connection invitation to them , edit profile and more. 
You can find sql queries in *Queries file, database logics in *Manager file and request handlers in corresponding files and also database models pdf is available!
Persian project description is in same directory too!
Of course there are some little bugs on the project but i did my best to fix them :)

<img src = "./demo/8.PNG" width = "200">



## How to Use

```
just run app.java
```


## Note
1- this program doesn't have GUI and you should work with Command Promt.
2- libraries used: io.github.cdimascio.dotenv.Dotenv for env variables;
3- add com.mysql.cj.jdbc.Driver to your project library and use your own url,username & password for dbsm

License
=======

    Copyright 2023 Mohammad Sadra Sarparandeh
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.