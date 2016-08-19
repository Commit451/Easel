# Easel
Tint Android widgets and views with ease

[![Build Status](https://travis-ci.org/Commit451/Easel.svg?branch=master)](https://travis-ci.org/Commit451/Easel) [![](https://jitpack.io/v/Commit451/Easel.svg)](https://jitpack.io/#Commit451/Easel)

# Gradle Dependency
Add the jitpack url to the project:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
then, in your app `build.gradle`
```groovy
dependencies {
    compile 'com.github.Commit451:Easel:1.0.0'
}
```

# Usage
See sample project for clear usage. Mostly looks like this:
```java
Easel.setTint(checkBox, color);
```

License
--------

    Copyright 2015 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
