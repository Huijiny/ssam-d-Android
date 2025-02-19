package com.mashup.presentation.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.*
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    loginToOnBoarding: () -> Unit,
    handleOnBackPressed: () -> Unit
) {
    val pagerState = rememberPagerState(0)

    LaunchedEffect(loginViewModel.currentPage) {
        pagerState.animateScrollToPage(loginViewModel.currentPage)
    }

    BackHandler(enabled = true) {
        when (loginViewModel.currentPage) {
            0 -> handleOnBackPressed()
            else -> loginViewModel.backPage()
        }
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
            ),
        pageCount = 3,
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> LoginContentScreen (
                onLoginButtonClicked = loginViewModel::handleKakaoLogin
            )
            1 -> NicknameScreen(
                onNextButtonClicked = { nickname ->
                    loginViewModel.setNicknameAndAddPage(nickname)
                },
            )
            2 -> LoginCompletionScreen (
                onStartButtonClicked = loginToOnBoarding,
                nickname = loginViewModel.nickname
            )
        }
    }
}

@Composable
fun LoginContentScreen(
    onLoginButtonClicked: () -> Unit
) {
    Box {
        LoginBackground()

        LoginContainer(modifier = Modifier.padding(top = 120.dp, bottom = 48.dp)) {
            LoginTitle(modifier = Modifier.padding(bottom = 24.dp))

            LoginPlanetLottie(modifier = Modifier
                .fillMaxWidth()
                .height(279.dp))

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(53.dp)
            ) {
                KakaoLoginButton(modifier = Modifier.padding(horizontal = 4.dp)) {
                    onLoginButtonClicked()
                }

                LoginGuideText()
            }
        }
    }
}

@Composable
private fun LoginBackground() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.img_space),
        contentDescription = stringResource(R.string.login_description_space),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun LoginContainer(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
private fun LoginTitle(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier
                .height(61.dp)
                .width(209.dp),
            painter = painterResource(R.drawable.img_keylink),
            contentDescription = stringResource(R.string.login_description_keylink)
        )

        KeyLinkMintText(
            text = stringResource(R.string.login_title),
            textStyle = Title1
        )
    }
}

@Composable
private fun LoginPlanetLottie(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottie_splash_planet)
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

@Composable
private fun KakaoLoginButton(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
    Image(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth()
            .clickable { onLoginButtonClicked() },
        painter = painterResource(R.drawable.img_kakao_login),
        contentDescription = stringResource(R.string.login_description_kakao_btn)
    )
}

@Composable
private fun LoginGuideText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(R.string.login_privacy_policy),
        textAlign = TextAlign.Center,
        color = Gray06,
        style = Caption
    )
}

@Composable
fun NicknameScreen(onNextButtonClicked: (String) -> Unit){
    var nickname by remember { mutableStateOf("") }
    var validation by remember { mutableStateOf(ValidationState.EMPTY) }
    val expectedText = "올바른 닉네임"
    val focusManager = LocalFocusManager.current

    Box {
        LoginBackground()

        LoginContainer(modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .padding(top = 185.dp, start = 20.dp, end = 20.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                KeyLinkMintText(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    text = stringResource(R.string.login_nickname),
                    textStyle = Heading3,
                    textAlign = TextAlign.Left
                )

                KeyLinkBoxTextField(
                    value = nickname,
                    onValueChange = { value ->
                        nickname = value
                        checkValidation(nickname, expectedText) { validationState ->
                            validation = validationState
                        }
                    },
                    hint = stringResource(R.string.login_nickname_hint),
                    maxLength = 10,
                    fontSize = 32.sp,
                    validationState = validation
                )
            }

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp).imePadding(),
                text = stringResource(R.string.login_next_btn),
                onClick = {
                    focusManager.clearFocus()
                    onNextButtonClicked(nickname)
                },
                enable = validation == ValidationState.SUCCESS
            )
        }
    }
}

@Composable
fun LoginCompletionScreen(onStartButtonClicked: () -> Unit, nickname: String) {
    Box {
        LoginBackground()

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.img_blueplanet),
            contentDescription = stringResource(R.string.login_description_blueplanet)
        )

        LoginContainer(modifier = Modifier.padding(top = 112.dp, bottom = 72.dp, start = 20.dp, end = 20.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                LoginProfileImage(modifier = Modifier.padding(bottom = 24.dp))
                LoginCompletionText(nickname = nickname)
            }

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_completion_btn),
                onClick = onStartButtonClicked
            )
        }
    }
}

@Composable
private fun LoginCompletionText(modifier: Modifier = Modifier, nickname: String) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = nickname + stringResource(R.string.login_completion_title),
        color = White,
        style = Title1
    )
}

@Composable
private fun LoginProfileImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape),
        painter = painterResource(R.drawable.img_profile_01),
        contentDescription = stringResource(R.string.login_description_profile_img),
        contentScale = ContentScale.Crop
    )
}
