package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Article
import uz.toshshahartransxizmat.toshbustravel.domain.model.News

fun Article.toNews(): News {
    return News(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = source?.name,
        sourceId = source?.id,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}