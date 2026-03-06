# Easel
Tint and color Android views with ease

[![Build](https://github.com/Commit451/Easel/actions/workflows/ci.yml/badge.svg)](https://github.com/Commit451/Easel/actions/workflows/ci.yml) [![Maven Central](https://img.shields.io/maven-central/v/com.commit451/easel.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/com.commit451/easel)

# Gradle Dependency
Add the library to your app `build.gradle`:
```gradle
dependencies {
    implementation("com.commit451:easel:latest.version.here")
}
```

# Usage
See sample project for clear usage. Mostly looks like this:
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

    Copyright 2018 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
