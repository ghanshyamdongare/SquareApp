package com.gd.gateway.di

import com.gd.domain.gateway.SquareGateway
import com.gd.gateway.SquareGatewayImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GatewayModule {
    @Binds
    fun bindSquareGateway(squareGatewayImpl: SquareGatewayImpl): SquareGateway
}
