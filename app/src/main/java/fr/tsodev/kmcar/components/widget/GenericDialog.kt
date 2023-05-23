package fr.tsodev.kmcar.components.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
) {


    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        content = {
            Surface(
                modifier = Modifier.padding(6.dp),
                color = MaterialTheme.colorScheme.surface
            ) {


                Column(
                    modifier = Modifier.padding(12.dp),
                ) {

                    Text(
                        text = title,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    if (description != null) {
                        Text(
                            text = description,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        if (negativeAction != null) {
                            Button(
                                modifier = Modifier.padding(end = 8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                onClick = negativeAction.onNegativeAction
                            ) {
                                Text(text = negativeAction.negativeBtnTxt)
                            }
                        }
                        if (positiveAction != null) {
                            Button(
                                modifier = Modifier.padding(end = 8.dp),
                                onClick = positiveAction.onPositiveAction,
                            ) {
                                Text(text = positiveAction.positiveBtnTxt)
                            }
                        }
                    }
                }
            }
        }
    )

}

data class PositiveAction(
    val positiveBtnTxt: String,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: String,
    val onNegativeAction: () -> Unit,
)


class GenericDialogInfo
private constructor(builder: GenericDialogInfo.Builder){

    val title: String
    val onDismiss: () -> Unit
    val description: String?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if(builder.title == null){
            throw Exception("GenericDialog title cannot be null.")
        }
        if(builder.onDismiss == null){
            throw Exception("GenericDialog onDismiss function cannot be null.")
        }
        this.title = builder.title!!
        this.onDismiss = builder.onDismiss!!
        this.description = builder.description
        this.positiveAction = builder.positiveAction
        this.negativeAction = builder.negativeAction
    }

    class Builder {

        var title: String? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var description: String? = null
            private set

        var positiveAction: PositiveAction? = null
            private set

        var negativeAction: NegativeAction? = null
            private set

        fun title(title: String): Builder{
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder{
            this.onDismiss = onDismiss
            return this
        }

        fun description(
            description: String
        ): Builder{
            this.description = description
            return this
        }

        fun positive(
            positiveAction: PositiveAction?,
        ) : Builder {
            this.positiveAction = positiveAction
            return this
        }

        fun negative(
            negativeAction: NegativeAction
        ) : Builder {
            this.negativeAction = negativeAction
            return this
        }

        fun build() = GenericDialogInfo(this)
    }
}
