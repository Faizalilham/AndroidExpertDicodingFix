package coding.faizal.rakaminchallenge.favorite.di

import android.content.Context
import coding.faizal.rakaminchallenge.di.FavoriteModuleDependencies
import coding.faizal.rakaminchallenge.favorite.presentation.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {


    // ACTIVITY
    fun inject(activity : FavoriteActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context : Context):Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies):Builder
        fun build():FavoriteComponent
    }
}