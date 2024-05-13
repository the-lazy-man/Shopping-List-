package com.example.mynotesapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.net.ContentHandler

// properties of items in the shopping app

data class ShoppingItem(val Id : Int,
                        var name : String,
                        var quantity : Int,
                        val isEditing : Boolean = false
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListMainFuntion(){
    var shopItems by remember{ mutableStateOf(listOf<ShoppingItem>()) }
    var showAlertBox by remember{ mutableStateOf(false) }
    var itemName by remember{ mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {showAlertBox = true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            items(shopItems) {
                item->
                if(item.isEditing){
                   ShoppingItemEditView(Item = item, onEditCompleted = {
                       editedName, editedQuantity ->
                       shopItems = shopItems.map{it.copy(isEditing = false)}
                       val editedItem = shopItems.find{it.Id == item.Id}
                       editedItem?.let {
                           it.name = editedName
                           it.quantity = editedQuantity
                       }
                   })
                }else {
                    ShoppingListView(item = item,
                        onEditClick = {
                            // finding out which item are we editing and change "isEditing boolean" to true
                            shopItems = shopItems.map { it.copy(isEditing = it.Id == item.Id) }
                        },
                        onDeleteClick = {
                            shopItems = shopItems - item
                        }
                    )
                }
            }
        }
    }
    if(showAlertBox){
        AlertDialog(onDismissRequest = { showAlertBox = false },
            confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if(itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                        val newItem = ShoppingItem(
                            Id = shopItems.size + 1,
                            name = itemName,
                            quantity = itemQuantity.toInt()
                        )
                        shopItems = shopItems + newItem
                        showAlertBox = false
                        itemName = ""
                        itemQuantity = ""
                    }
                }) {
                    Text(text = "Add")
                }
                Button(onClick = { showAlertBox = false }) {
                        Text(text = "Cancel")
                }
            }
        },
            title = {Text(text = "Add Shopping Item")},
            text = {
                Column {
                    OutlinedTextField(value = itemName,
                        onValueChange = {itemName = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(value = itemQuantity,
                        onValueChange = {itemQuantity = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
            )
            
        
    }
}
@Composable
fun ShoppingListView(
    item : ShoppingItem,
    onEditClick : () -> Unit,
    onDeleteClick : () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = item.quantity.toString(), modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }   
}
@Composable
fun ShoppingItemEditView(Item: ShoppingItem, onEditCompleted : (String,Int) -> Unit){
    var editName by remember {(mutableStateOf(Item.name))}
    var editQuantity by remember {(mutableStateOf(Item.quantity.toString()))}
    var isEditing by remember {(mutableStateOf(Item.isEditing))}
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp)
        .border(
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(20)
        ),
        horizontalArrangement = Arrangement.SpaceEvenly,

    ){
            Column {
                BasicTextField(value = editName, onValueChange = {editName = it},
                    singleLine = true,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
                BasicTextField(value = editQuantity, onValueChange = {editQuantity = it},
                    singleLine = true,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
            }
        Button(onClick = {
            isEditing = false
            onEditCompleted(editName,editQuantity.toIntOrNull() ?:1)
        }) {
            Text(text = "Save")
        }
    }

}