﻿# JPHol
日本の祝日判定する奴  
Kotlinで書いた

#### gradle
	repositories {
		mavenCentral()
		maven { url "https://raw.github.com/MeilCli/JPHol/master/repository" }
		maven { url "https://raw.github.com/MeilCli/KLinq/master/klinq/repository" }
	}
	
	dependencies {
		compile 'meilcli:jphol:1.0'
 		compile 'meilcli:klinq:1.2'
	}


#### Java
	HolidayManager manager = new HolidayManager();
	boolean isHoliday = manager.isHoliday(new GregorianCalendar());

Javaだとたぶんこんな感じで呼び出せる


ライセンス
----------

This source is The MIT License.

using [The Kotlin Standard Library][Kotlin_stdlib] [Apache License, Version 2.0][Apache]
using [KLinq](https://github.com/MeilCli/KLinq) [The MIT License](https://github.com/MeilCli/KLinq/blob/master/LICENSE)
[Apache]: http://www.apache.org/licenses/LICENSE-2.0
[Kotlin_stdlib]: https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib
