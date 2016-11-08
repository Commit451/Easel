# Easel
Tint and color Android views with ease

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
    compile 'com.github.Commit451.Easel:easel:latest.version.here'
    //and, for kotlin extensions
    compile 'com.github.Commit451.Easel:easel-kotlin:latest.version.here'
}
```

# Usage
See sample project for clear usage. Mostly looks like this:
```java
Easel.tint(checkBox, color);
```
If you are using Kotlin, extensions methods make it even easier:
```kotlin
checkbox.tint(color)
```

# Supported Views
Currently, you can tint the following views at run time:
- Button
- Checkbox
- EditText
- ProgressBar
- RadioButton
- SeekBar
- Spinner
- SwitchCompat
- TextView

In addition, you can also tint other things which are somewhat difficult to tint in Android, such as:
- Drawable
- TextView cursor
- TextView handles
- TextView selection highlight
- MenuItem
- Toolbar overflow
- View Edge effect (on scroll)

# Thanks
Props to the project [Material Dialogs](https://github.com/afollestad/material-dialogs/blob/master/core/src/main/java/com/afollestad/materialdialogs/internal/MDTintHelper.java) where a lot of the tinting code came from.

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
