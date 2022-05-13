package com.technokratos.app.router

import com.example.feature_timetable.TimetableRouter
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter

interface Router :
    SplashRouter,
    AuthRouter,
    MainRouter,
    NavigateBackRouter,
    TimetableRouter