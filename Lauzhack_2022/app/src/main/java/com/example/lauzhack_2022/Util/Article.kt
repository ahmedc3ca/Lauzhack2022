package com.example.lauzhack_2022.Util

import java.time.LocalDate

data class Article(var name: String, var footprint: Double)

data class StorageEntry(var date: LocalDate, var articles: List<Article>)

data class Storage(var entries: List<StorageEntry>)