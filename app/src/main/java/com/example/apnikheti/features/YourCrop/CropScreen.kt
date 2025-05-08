import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.apnikheti.R
import com.example.apnikheti.features.YourCrop.data.Crop
import com.example.apnikheti.features.YourCrop.data.CropData
import com.example.apnikheti.viewModel.selectedCrop.CropViewModel.CropViewModel

@Composable
fun CropScreen(cropData: CropData) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        cropData.run {
            item { CategoryTitle(title = "Horticulture Crops") }
            items(horticulture_crops) { crop ->
                CropItem(crop)
            }
            item { CategoryTitle(title = "Field Crops") }
            items(field_crops) { crop ->
                CropItem(crop)
            }
            item { CategoryTitle(title = "Oilseed Crops") }
            items(oilseed_crops) { crop ->
                CropItem(crop)
            }
            item { CategoryTitle(title = "Selected Crops") }
            items(selected_crops) { crop ->
                CropItem(crop)
            }
        }
    }
}

@Composable
fun CategoryTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(vertical = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CropItem(crop: Crop) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = crop.image,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.error_image)
                ),
                contentDescription = crop.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = crop.name,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}
