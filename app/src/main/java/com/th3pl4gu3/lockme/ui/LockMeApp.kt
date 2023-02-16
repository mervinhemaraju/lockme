package com.th3pl4gu3.lockme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.th3pl4gu3.lockme.R
import com.th3pl4gu3.lockme.ui.theme.LockMeTheme

@Composable
fun LockMeApp(
    closeAppAction: () -> Unit,
    openSettingsUiAction: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Welcome to",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.weight(1f))

        AppNameAndLogo()

        Spacer(modifier = Modifier.weight(1f))

        AppMessage()

        Spacer(modifier = Modifier.weight(1f))

        AppActions(
            closeAppAction = closeAppAction,
            openSettingsUiAction = openSettingsUiAction
        )
    }
}

@Composable
fun AppActions(
    closeAppAction: () -> Unit,
    openSettingsUiAction: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = closeAppAction,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Close")
        }
        Button(
            onClick = openSettingsUiAction,
            modifier = Modifier.weight(1f)
        )
        {
            Text(text = "Proceed")
        }
    }
}

@Composable
fun AppMessage() {
    Text(
        text = "Lock Me is an application that will allow you to lock your phone by just tapping on the application icon on your launcher",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )

    Text(
        text = "Lock Me makes use of the Accessibility Service API in your phone to be able to perform these lock operations. Lock Me does not collect, store or use any personal data whatsoever, neither locally or remotely.",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )

    Text(
        text = "Lock Me uses Accessibility Service for the sole purpose of doing screen lock operations and nothing else.",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )

    Text(
        text = "To continue, click the Proceed button. This will navigate you to the Accessibility Service settings on your phone. You will need to allow Lock Me there to get started.",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )
}

@Composable
fun AppNameAndLogo() {
    Image(
        ImageVector.vectorResource(id = R.drawable.ic_lock_me),
        contentDescription = "App Logo"
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Lock Me",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview("Lock Me App")
@Composable
fun LockMeAppPreview() {
    LockMeTheme {
        LockMeApp(
            closeAppAction = {},
            openSettingsUiAction = {}
        )
    }
}