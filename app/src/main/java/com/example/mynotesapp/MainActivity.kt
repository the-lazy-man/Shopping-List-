package com.example.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynotesapp.ui.theme.MyNotesAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShoppingListMainFuntion()
//                    var shopItems by remember{mutableStateOf(listOf<ShoppingItem>())}
//                  Column(
//                      modifier = Modifier.fillMaxSize(),
//                      verticalArrangement = Arrangement.Center
//                  ){
//                      Button(
//                          onClick = {},
//                          modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                          Text(text = "Add Item")
//                      }
//                      LazyColumn(modifier = Modifier.fillMaxSize().padding(15.dp)){
//                            items(shopItems){
//
//                            }
  //                    }
  //                }
                }
            }
        }
    }
}
//// properties of items in the shopping app
//data class ShoppingItem(val Id : Int,
//                        val name : String,
//                        val quantity : Int,
//                        val isEditing : Boolean = false
//)