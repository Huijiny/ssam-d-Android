package com.mashup.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkMintText

@Composable
fun LoginScreen(
    onLoginButtonClicked: () -> Unit
) {
    Box {
        LoginBackground()
        
        Scaffold(
            modifier = Modifier.padding(vertical = 120.dp),
            backgroundColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginTitle(modifier = Modifier.padding(bottom = 24.dp))

                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.img_planet),
                    contentDescription = stringResource(R.string.login_description_planet)
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .height(52.dp)
                        .width(312.dp)
                        .clickable { onLoginButtonClicked() },
                    painter = painterResource(R.drawable.img_kakao_login),
                    contentDescription = stringResource(R.string.login_description_kakao_btn)
                )
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
private fun LoginTitle(modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            modifier = Modifier
                .height(61.dp)
                .width(209.dp),
            painter = painterResource(R.drawable.img_keylink),
            contentDescription = stringResource(R.string.login_description_keylink)
        )

        KeyLinkMintText(text = stringResource(R.string.login_title))
    }
}
