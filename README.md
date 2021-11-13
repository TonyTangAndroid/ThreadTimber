# ThreadTimber

## What is this library about?
**ThreadTimber** is a simple in-app Timber logger with thread info attached as prefix. 


## Features
1. By default, it adds the thread name with `[main]` or `[Thread-2]` on the logged message as prefix.
2. You could add your own prefix prior the thread name by passing the constructor parameter. 
For example, if you pass `tony`, the whole prefix prepend at the message will be `[tony#main]` & `[tony#Thread-2]`.
3. The rest of feature sets follows the `Timber.DebugLog` and you no longer have to plan the default `Timber.DebugLog`.

## Setup

### Download

Based on your IDE you can import library in one of the following ways

##### Gradle:
1, Add JitPack at your root level `build.gradle` file.
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

//com.github.UsherAndroid:external

2, Add the dependency in your `build.gradle` file : 
```gradle
implementation 'com.github.TonyTangAndroid:ThreadTimber:0.3'
```

### Usage

In your application code, create an instance of `WoodTree` and add it as an Timber tree when planting your Timber tree:

```java
Timber.plant(new ThreadTree("tony"));
```

That's it! 
 
### Usage

For now, it does not support timber 5.0.1 per https://github.com/TonyTangAndroid/ThreadTimber/pull/1

## Acknowledgements
Timber (source repo)
- [Timber][timberLink] - Copyright 2013 Jake Wharton.
##### Thanks to [Jake Wharton][jakeWhartonLink] for his amazing library [Timber][timberLink].

Wood uses the following open source libraries:
- [Timber][timberLink] - Copyright Jake Wharton.

License
-------

    Copyright (C) 2019 Tony Tang.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
[timberLink]: https://github.com/JakeWharton/timber
[jakeWhartonLink]: https://github.com/JakeWharton
[timberLink]: https://github.com/JakeWharton/timber
