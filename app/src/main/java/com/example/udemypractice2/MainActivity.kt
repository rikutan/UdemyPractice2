package com.example.udemypractice2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.udemypractice2.ui.theme.ButtonColor
import com.example.udemypractice2.ui.theme.UdemyPractice2Theme

class MainActivity : ComponentActivity() {

    // MainActivity から MainViewModel にアクセスできるようになる
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UdemyPractice2Theme {
//                MainScreen()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 40.dp,
                            bottom = 20.dp
                        ),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "BMI計算アプリ",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.height(30.dp))


                    // 身長
//                    var text by remember { mutableStateOf("") }
                    PinkLabeledTextField(
                        value = viewModel.height,
//                        value = text,
                        // it = ユーザーに入力された値
                        onValueChange = { viewModel.height = it },
//                        onValueChange = { text = it },
                        label = "身長(cm)",
                        placeholder = "170",
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    // 体重
                    PinkLabeledTextField(
                        value = viewModel.weight,
                        onValueChange = { viewModel.weight = it },
                        label = "体重(kg)",
                        placeholder = "65",
                    )
                    Spacer(modifier = Modifier.height(30.dp))


                    // 計算する
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                        onClick = { viewModel.calculateBMI() },
                    ) {
                        Text(
                            text = "計算する",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    // クリアボタン
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                        onClick = { viewModel.clearTextField() },
                    ) {
                        Text(
                            text = "クリア",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))


                    // ifの条件が false のため、この if自体のブロックをスキップする
                    // viewModel内の処理で trueになったら表示する
                    if (viewModel.isResultShown) {
                        // BMI 結果表示テキスト
                        Text(
                            text = "あなたのBMIは ${viewModel.bmi} です",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        // BMI 判定テキスト
                        Text(
                            text = viewModel.result,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PinkLabeledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
) {
    Column {
        Text(
            text = label,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
        )

        // 入力欄、value=入力欄に表示する文字列、onValueChange=入力欄に値が入力されたときの処理を担当
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(

                // テキストフィールドの背景色を変更、Transparent は、透明化する
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            placeholder = { Text(text = placeholder,color = Color.Gray) },

            // キーボードでの入力を数字にする
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            // テキストフィールドの入力表示を制御する trueなら一行、falseなら複数行、入力可能
            singleLine = true,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UdemyPractice2Preview() {
    UdemyPractice2Theme {
    }
}