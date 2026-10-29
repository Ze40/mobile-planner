package com.example.planner.ui.theme

import androidx.compose.ui.graphics.Color

/** Color tokens adapted from Taiga UI's light and dark palettes. */
object ColorTokens {
    val AccentOne = Color(0xFF526ED3)
    val AccentOneHover = Color(0xFF6C86E2)
    val AccentOnePressed = Color(0xFF314692)
    val AccentTwo = Color(0xFFFF7043)
    val AccentTwoHover = Color(0xFFFF9A94)
    val AccentTwoPressed = Color(0xFFE7716A)

    object Light {
        val BackgroundBase = Color(0xFFFFFFFF)
        val BackgroundBaseAlt = Color(0xFFF6F6F6)
        val BackgroundNeutralOne = Color(0x0A000000)
        val BackgroundNeutralOneHover = Color(0x14000000)
        val BackgroundNeutralOnePressed = Color(0x1F000000)
        val BackgroundNeutralTwo = Color(0x14000000)
        val BackgroundNeutralTwoHover = Color(0x1A000000)
        val BackgroundNeutralTwoPressed = Color(0x24000000)
        val BackgroundAccentOpposite = Color(0xFF000000)
        val BackgroundAccentOppositeHover = Color(0xFF333333)
        val BackgroundAccentOppositePressed = Color(0xFF808080)
        val BackgroundElevationOne = Color(0xFFFFFFFF)
        val BackgroundElevationTwo = Color(0xFFFFFFFF)
        val BackgroundElevationThree = Color(0xFFFFFFFF)

        val ServiceAutofillBackground = Color(0xFFFFF5C0)
        val ServiceSelectionBackground = Color(0x1E70B6F6)
        val ServiceBackdrop = Color(0xBF000000)

        val BorderNormal = Color(0x1A000000)
        val BorderHover = Color(0x29000000)
        val BorderFocus = Color(0xA3333333)

        val StatusNegative = Color(0xFFFF291A)
        val StatusNegativePale = Color(0x14F52222)
        val StatusNegativePaleHover = Color(0x29F52222)
        val StatusPositive = Color(0xFF4AC99B)
        val StatusPositivePale = Color(0x1F4AC99B)
        val StatusPositivePaleHover = Color(0x3D4AC99B)
        val StatusWarning = Color(0xFFFFC700)
        val StatusWarningPale = Color(0x1FFFC700)
        val StatusWarningPaleHover = Color(0x3DFFC700)
        val StatusInfo = Color(0xFF70B6F6)
        val StatusInfoPale = Color(0x1E70B6F6)
        val StatusInfoPaleHover = Color(0x3D70B6F6)
        val StatusNeutral = Color(0xFF79818C)

        val TextPrimary = Color(0xFF1B1F3B)
        val TextSecondary = Color(0xA61B1F3B)
        val TextTertiary = Color(0x661B1F3B)
        val TextPrimaryOnAccentOne = Color(0xFFFFFFFF)
        val TextPrimaryOnAccentTwo = Color(0xFFFFFFFF)
        val TextAction = Color(0xFF4C66C3)
        val TextActionHover = Color(0xFF6C86E2)
        val TextPositive = Color(0xFF3AA981)
        val TextPositiveHover = Color(0xFF7AC5AA)
        val TextNegative = Color(0xFFFF291A)
        val TextNegativeHover = Color(0xFFFF4D4D)
    }

    object Dark {
        val BackgroundBase = Color(0xFF222222)
        val BackgroundBaseAlt = Color(0xFF333333)
        val BackgroundNeutralOne = Color(0x14FFFFFF)
        val BackgroundNeutralOneHover = Color(0x29FFFFFF)
        val BackgroundNeutralOnePressed = Color(0x3DFFFFFF)
        val BackgroundNeutralTwo = Color(0x3DFFFFFF)
        val BackgroundNeutralTwoHover = Color(0x52FFFFFF)
        val BackgroundNeutralTwoPressed = Color(0x66FFFFFF)
        val BackgroundAccentOpposite = Color(0xFFFFFFFF)
        val BackgroundAccentOppositeHover = Color(0xFFF6F6F6)
        val BackgroundAccentOppositePressed = Color(0xFFEDEDED)
        val BackgroundElevationOne = Color(0xFF292929)
        val BackgroundElevationTwo = Color(0xFF2F2F2F)
        val BackgroundElevationThree = Color(0xFF373737)

        val ServiceAutofillBackground = Color(0xFF554A2A)
        val ServiceSelectionBackground = Light.ServiceSelectionBackground
        val ServiceBackdrop = Light.ServiceBackdrop

        val BorderNormal = Color(0x24FFFFFF)
        val BorderHover = Color(0x99FFFFFF)
        val BorderFocus = Color(0xA3FFFFFF)

        val StatusNegative = Color(0xFFFF4D4D)
        val StatusNegativePale = Color(0x3DFF4D4D)
        val StatusNegativePaleHover = Color(0x52FF4D4D)
        val StatusPositive = Color(0xFF4AC99B)
        val StatusPositivePale = Color(0x524AC99B)
        val StatusPositivePaleHover = Color(0x664AC99B)
        val StatusWarning = Color(0xFFFFC700)
        val StatusWarningPale = Color(0x52FFC700)
        val StatusWarningPaleHover = Color(0x66FFC700)
        val StatusInfo = Color(0xFF70B6F6)
        val StatusInfoPale = Color(0x5270B6F6)
        val StatusInfoPaleHover = Color(0x6670B6F6)
        val StatusNeutral = Color(0xFF959BA4)

        val TextPrimary = Color(0xFFFFFFFF)
        val TextSecondary = Color(0xB8FFFFFF)
        val TextTertiary = Color(0x99FFFFFF)
        val TextPrimaryOnAccentOne = Light.TextPrimaryOnAccentOne
        val TextPrimaryOnAccentTwo = Light.TextPrimaryOnAccentTwo
        val TextAction = Color(0xFF6788FF)
        val TextActionHover = Color(0xFF526ED3)
        val TextPositive = Color(0xFF44C596)
        val TextPositiveHover = Color(0xFF3AA981)
        val TextNegative = Color(0xFFFF4D4D)
        val TextNegativeHover = Color(0xFFFF6666)
    }

    val ChartCategorical = listOf(
        AccentOne,
        Color(0xFFEA97C4),
        Color(0xFFA0C5DF),
        Color(0xFFFEE797),
        Color(0xFFB0B0B0),
        Color(0xFFE29398),
        Color(0xFFB8474E),
        Color(0xFFFCC068),
        Color(0xFFFF8A00),
        Color(0xFFDAB3F9),
        Color(0xFF7B439E),
        Color(0xFF8DDA71),
        Color(0xFFFCBB14),
        Color(0xFFA8CEF1),
        Color(0xFFBD65A4),
        Color(0xFF7FD7CC),
        Color(0xFF2FAD96),
        Color(0xFFD4ACA2),
        Color(0xFF9D6F64),
        Color(0xFFD2E9A2),
        Color(0xFFAADC42),
        Color(0xFF3682DB),
        Color(0xFF34B41F)
    )
}
