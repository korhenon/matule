package com.example.matule.domain.converters

import com.example.matule.data.model.Shoe
import com.example.matule.data.model.ShoeDto

fun ShoeDto.toUiModel(isFavorite: Boolean): Shoe {
    return Shoe(
        this.id,
        this.name,
        this.description,
        this.image,
        this.price,
        this.category,
        isFavorite
    )
}