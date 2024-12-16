package com.drdisagree.colorblendr.utils

import com.drdisagree.colorblendr.common.Const.COLOR_THEME_APP_ICON
import com.drdisagree.colorblendr.common.Const.HOMESCREEN_PREVIEW_IMAGE_STATUS
import com.drdisagree.colorblendr.common.Const.LOCK_ADAPTIVE_COLOR
import com.drdisagree.colorblendr.common.Const.NAVIGATIONBAR_USE_THEME_DEFAULT
import com.drdisagree.colorblendr.common.Const.THEME_CUSTOMIZATION_OVERLAY_PACKAGES
import com.drdisagree.colorblendr.common.Const.WALLPAPER_THEME_COLORS
import com.drdisagree.colorblendr.common.Const.WALLPAPER_THEME_COLORS_FOR_GOOGLE
import com.drdisagree.colorblendr.common.Const.WALLPAPER_THEME_COLOR_IS_GRAY
import com.drdisagree.colorblendr.common.Const.WALLPAPER_THEME_STATE
import com.topjohnwu.superuser.Shell

object SamsungPalette {

    fun applySystemColors(jsonString: String, paletteArray: String) {
        Shell.cmd(
            "settings put secure $THEME_CUSTOMIZATION_OVERLAY_PACKAGES '$jsonString'",
            "settings put system $WALLPAPER_THEME_STATE '1'",
            "settings put system $LOCK_ADAPTIVE_COLOR '3'",
            "settings put system $WALLPAPER_THEME_COLORS '$paletteArray'",
            "settings put system $WALLPAPER_THEME_COLORS_FOR_GOOGLE '$paletteArray'",
            "settings put system $WALLPAPER_THEME_COLOR_IS_GRAY '0'",
            "settings put system $HOMESCREEN_PREVIEW_IMAGE_STATUS '1'",
            "settings put global $NAVIGATIONBAR_USE_THEME_DEFAULT '1'"
        ).exec()
    }

    val isThemedIconEnabled: Boolean
        get() = Shell.cmd(
            "settings get global $COLOR_THEME_APP_ICON"
        ).exec().out[0] == "1"

    fun enableThemedIcon(enabled: Boolean) {
        Shell.cmd(
            "settings put global $COLOR_THEME_APP_ICON '${if (enabled) "1" else "0"}'"
        ).exec()
    }

    fun removeSystemColors() {
        Shell.cmd(
            "settings put secure $THEME_CUSTOMIZATION_OVERLAY_PACKAGES '${SystemPalette.originalSettings}'",
            "settings put system $WALLPAPER_THEME_STATE '0'",
            "settings put system $LOCK_ADAPTIVE_COLOR '1'",
            "settings put system $WALLPAPER_THEME_COLORS ''",
            "settings put system $WALLPAPER_THEME_COLORS_FOR_GOOGLE ''",
            "settings put global $COLOR_THEME_APP_ICON '0'",
            "settings put global $NAVIGATIONBAR_USE_THEME_DEFAULT '0'"
        ).exec()
    }
}