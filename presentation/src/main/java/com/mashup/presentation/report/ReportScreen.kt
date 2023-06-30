package com.mashup.presentation.report

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/01
 */
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 8.dp, start = 20.dp, end = 20.dp),
        ) {
            ReportHeader(modifier = Modifier.fillMaxWidth())

            ReportContent(modifier = Modifier)
        }
    }
}

@Composable
fun ReportContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(top = 28.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ReportTypeContent(reportType = stringResource(R.string.report_not_allowed))
        }
        item {
            ReportTypeContent(reportType = stringResource(R.string.report_advertisement))
        }
        item {
            ReportTypeContent(reportType = stringResource(R.string.report_addictive))
        }
        item {
            ReportTypeContent(reportType = stringResource(R.string.report_disallowed_link))
        }
        item {
            ReportTypeContent(reportType = stringResource(R.string.report_religion))
        }
    }
}

@Composable
fun ReportTypeContent(
    reportType: String,
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = reportType,
                style = Body1,
                color = White
            )
            Icon(
                modifier = Modifier.clickable { onIconClick() },
                painter = painterResource(id = R.drawable.ic_chevron_right_24),
                tint = White,
                contentDescription = stringResource(R.string.content_description_report),
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Gray03,
            thickness = 1.dp
        )
    }
}

@Composable
fun ReportHeader(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.report_title),
        style = Heading3,
        color = White
    )
}

@Preview(showBackground = true, backgroundColor = 0XFFFFFFFF)
@Composable
private fun ReportScreenPreview() {
    SsamDTheme {
        ReportScreen()
    }
}
