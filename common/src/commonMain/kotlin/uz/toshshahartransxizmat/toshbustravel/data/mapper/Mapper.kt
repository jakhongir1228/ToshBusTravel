package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Article
import uz.toshshahartransxizmat.toshbustravel.data.model.response.Data
import uz.toshshahartransxizmat.toshbustravel.domain.model.News
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData

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

fun Data.toSignUpData():SignUpData{
    return SignUpData(
        code = code,
        hash = hash
    )
}