package fr.tsodev.kmcar.components

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import fr.tsodev.kmcar.R


@Composable
fun QrCodeComposable(data: String) {
//    val qrCodeContent = stringResource(R.string.qr_code_content, data)
    val qrCodeBitmap = getQrCodeBitmap(data) // using the ZXing library
    Image(bitmap = qrCodeBitmap.asImageBitmap(), contentDescription = null)
}

fun getQrCodeBitmap (data: String): Bitmap {
    val size = 512 //pixels
    val qrCodeContent = data
    val hints = hashMapOf<EncodeHintType, Int> ().also { it [EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
    val bits = QRCodeWriter ().encode (qrCodeContent, BarcodeFormat.QR_CODE, size, size)
    return Bitmap.createBitmap (size, size, Bitmap.Config.RGB_565).also {
        for (x in 0 until size) {
            for (y in 0 until size) {
                it.setPixel (x, y, if (bits [x, y]) Color.BLACK else Color.WHITE)
            }
        }
    }
}
