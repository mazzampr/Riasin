package com.frontend.riasin.ui.theme.pemesanan

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.PrimaryLight
import com.frontend.riasin.ui.theme.PrimaryLight2
import com.frontend.riasin.ui.theme.RiasinTheme
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pemesanan2Screen(
    muaName: String = "Laraz Makeup",
    serviceType: String = "Wisuda",
    onBackClick: () -> Unit = {},
    onPilihJadwal: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var currentMonth by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf<Int?>(null) }
    var isBooking2Days by remember { mutableStateOf(false) }
    var showRedDates by remember { mutableStateOf(true) }
    var showGreenDates by remember { mutableStateOf(true) }
    var selectedHour by remember { mutableStateOf("00") }
    var selectedMinute by remember { mutableStateOf("00") }
    var showTimePickerDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    // States for Booking 2 Days
    var day1Date by remember { mutableStateOf<String?>(null) }
    var day1Hour by remember { mutableStateOf("00") }
    var day1Minute by remember { mutableStateOf("00") }
    var day2Date by remember { mutableStateOf<String?>(null) }
    var day2Hour by remember { mutableStateOf("00") }
    var day2Minute by remember { mutableStateOf("00") }

    var showDatePickerDialog by remember { mutableStateOf(false) }
    var datePickerForDay by remember { mutableStateOf(1) } // 1 for day1, 2 for day2
    var showTimePickerForDay by remember { mutableStateOf(0) } // 0 = none, 1 = day1, 2 = day2

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pemesanan", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Title Section
            Text(
                text = "Pilih Jadwal Pemesanan",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tanggal dan jam berapa kamu ingin di-makeup?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LegendItem(
                    color = Color(0xFFFFCDD2),
                    text = "Tanggal berwarna merah tidak dapat di pesan"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LegendItem(
                    color = Color(0xFFC8E6C9),
                    text = "Tanggal berwarna hijau dapat di pesan"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Booking 2 Days Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Booking 2 Hari?",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Switch(
                    checked = isBooking2Days,
                    onCheckedChange = { isBooking2Days = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Primary,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (isBooking2Days) {
                // Two-Day Booking Form
                TwoDayBookingForm(
                    day1Date = day1Date,
                    day1Hour = day1Hour,
                    day1Minute = day1Minute,
                    day2Date = day2Date,
                    day2Hour = day2Hour,
                    day2Minute = day2Minute,
                    onDay1DateClick = {
                        datePickerForDay = 1
                        showDatePickerDialog = true
                    },
                    onDay1TimeClick = {
                        showTimePickerForDay = 1
                    },
                    onDay2DateClick = {
                        datePickerForDay = 2
                        showDatePickerDialog = true
                    },
                    onDay2TimeClick = {
                        showTimePickerForDay = 2
                    }
                )
            } else {
                // Original Calendar UI
                // Calendar Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val newMonth = currentMonth.clone() as Calendar
                        newMonth.add(Calendar.MONTH, -1)
                        currentMonth = newMonth
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Previous Month",
                            tint = Primary
                        )
                    }

                    Text(
                        text = currentMonth.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("id", "ID"))
                            ?.replaceFirstChar { it.uppercase() } ?: "Juli",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    IconButton(onClick = {
                        val newMonth = currentMonth.clone() as Calendar
                        newMonth.add(Calendar.MONTH, 1)
                        currentMonth = newMonth
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Next Month",
                            tint = Primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Day Headers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min").forEach { day ->
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Calendar Grid
                CalendarGrid(
                    calendar = currentMonth,
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it }
                )

                // Time Selection Section (appears when date is selected)
                if (selectedDate != null) {
                    Spacer(modifier = Modifier.height(24.dp))

                    TimeSelectionSection(
                        selectedHour = selectedHour,
                        selectedMinute = selectedMinute,
                        onTimeClick = { showTimePickerDialog = true }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Pilih Jadwal Button
            Button(
                onClick = {
                    if (isBooking2Days) {
                        onPilihJadwal(muaName)
                    } else {
                        selectedDate?.let {
                            onPilihJadwal(muaName)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                enabled = if (isBooking2Days) {
                    day1Date != null && day2Date != null
                } else {
                    selectedDate != null
                }
            ) {
                Text(
                    text = "Pilih Jadwal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Time Picker Bottom Sheet Dialog
    if (showTimePickerDialog) {
        TimePickerBottomSheet(
            sheetState = sheetState,
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            onDismiss = { showTimePickerDialog = false },
            onTimeSelected = { hour, minute ->
                selectedHour = hour
                selectedMinute = minute
                showTimePickerDialog = false
            }
        )
    }

    // Time Picker for Day 1 or Day 2
    if (showTimePickerForDay > 0) {
        val currentHour = if (showTimePickerForDay == 1) day1Hour else day2Hour
        val currentMinute = if (showTimePickerForDay == 1) day1Minute else day2Minute

        TimePickerBottomSheet(
            sheetState = sheetState,
            selectedHour = currentHour,
            selectedMinute = currentMinute,
            onDismiss = { showTimePickerForDay = 0 },
            onTimeSelected = { hour, minute ->
                if (showTimePickerForDay == 1) {
                    day1Hour = hour
                    day1Minute = minute
                } else {
                    day2Hour = hour
                    day2Minute = minute
                }
                showTimePickerForDay = 0
            }
        )
    }

    // Date Picker Dialog for Booking 2 Days
    if (showDatePickerDialog) {
        DatePickerDialog(
            currentMonth = currentMonth,
            onMonthChange = { newMonth -> currentMonth = newMonth },
            onDateSelected = { dateString ->
                if (datePickerForDay == 1) {
                    day1Date = dateString
                } else {
                    day2Date = dateString
                }
                showDatePickerDialog = false
            },
            onDismiss = { showDatePickerDialog = false }
        )
    }
}

@Composable
fun LegendItem(
    color: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 11.sp
        )
    }
}

@Composable
fun CalendarGrid(
    calendar: Calendar,
    selectedDate: Int?,
    onDateSelected: (Int) -> Unit
) {
    val cal = calendar.clone() as Calendar
    cal.set(Calendar.DAY_OF_MONTH, 1)
    val firstDayOfWeek = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7 // Monday = 0
    val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Unavailable dates (example: some dates are booked)
    val unavailableDates = setOf(1, 2, 3, 4, 5, 6)
    val today = Calendar.getInstance()

    Column {
        for (week in 0..5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (dayOfWeek in 0..6) {
                    val dayNumber = week * 7 + dayOfWeek - firstDayOfWeek + 1

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (dayNumber in 1..daysInMonth) {
                            val dateToCheck = calendar.clone() as Calendar
                            dateToCheck.set(Calendar.DAY_OF_MONTH, dayNumber)

                            val isSelected = dayNumber == selectedDate
                            val isUnavailable = unavailableDates.contains(dayNumber)
                            val isPastDate = dateToCheck.before(today)

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            isSelected -> Primary
                                            isUnavailable || isPastDate -> Color(0xFFFFCDD2)
                                            else -> Color(0xFFC8E6C9)
                                        }
                                    )
                                    .clickable(enabled = !isUnavailable && !isPastDate) {
                                        onDateSelected(dayNumber)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayNumber.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimeSelectionSection(
    selectedHour: String,
    selectedMinute: String,
    onTimeClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Pilih Waktu",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onTimeClick)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedHour.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = ":",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = selectedMinute.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Warning message
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFF3E0))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xFFFF9800),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Jadwal di bawah sudah terpakai. Silakan pilih waktu berbeda",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFE65100),
                fontSize = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Time slots
        TimeSlot(time = "04:00 - 07:00")
        Spacer(modifier = Modifier.height(8.dp))
        TimeSlot(time = "08:00 - 09:00")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerBottomSheet(
    sheetState: SheetState,
    selectedHour: String,
    selectedMinute: String,
    onDismiss: () -> Unit,
    onTimeSelected: (String, String) -> Unit
) {
    var tempHour by remember { mutableStateOf(selectedHour.toIntOrNull() ?: 0) }
    var tempMinute by remember { mutableStateOf(selectedMinute.toIntOrNull() ?: 0) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Time Pickers Row - No Container Background
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hour Picker
                ScrollableNumberPicker(
                    value = tempHour,
                    onValueChange = { tempHour = it },
                    range = 0..23,
                    modifier = Modifier.width(120.dp)
                )

                Spacer(modifier = Modifier.width(32.dp))

                // Minute Picker
                ScrollableNumberPicker(
                    value = tempMinute,
                    onValueChange = { tempMinute = it },
                    range = 0..59,
                    modifier = Modifier.width(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = com.frontend.riasin.ui.theme.PrimaryLight,
                        contentColor = Primary
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "Batalkan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        onTimeSelected(
                            tempHour.toString().padStart(2, '0'),
                            tempMinute.toString().padStart(2, '0')
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "Selesai",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableNumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    modifier: Modifier = Modifier
) {
    val itemHeight = 60.dp
    val visibleItemsCount = 5
    val coroutineScope = rememberCoroutineScope()

    // Create infinite list - larger buffer to prevent edge cases
    val listSize = 10000
    val centerOffset = listSize / 2

    val infiniteItems = remember(range) {
        List(listSize) { index ->
            val offset = index - centerOffset
            var v = range.first + offset
            val rangeSize = range.last - range.first + 1

            // Proper modulo wrapping
            while (v < range.first) v += rangeSize
            while (v > range.last) v -= rangeSize

            v
        }
    }

    // Find initial index for the current value
    val initialIndex = remember(value, range) {
        val targetIndex = infiniteItems.indexOfFirst { it == value }
        if (targetIndex != -1) {
            maxOf(0, targetIndex - 2) // Ensure non-negative
        } else {
            maxOf(0, centerOffset - 2)
        }
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialIndex
    )

    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    // Update value when scrolling stops
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val centerIndex = listState.firstVisibleItemIndex + 2
            if (centerIndex in infiniteItems.indices) {
                val newValue = infiniteItems[centerIndex]
                if (newValue != value && newValue in range) {
                    onValueChange(newValue)
                }
            }
        }
    }

    // Scroll to new value when changed externally
    LaunchedEffect(value) {
        val targetIndex = infiniteItems.indexOfFirst { it == value }
        if (targetIndex != -1) {
            val currentCenter = listState.firstVisibleItemIndex + 2
            if (kotlin.math.abs(targetIndex - currentCenter) > 1) {
                val scrollToIndex = maxOf(0, targetIndex - 2) // Ensure non-negative
                listState.animateScrollToItem(scrollToIndex)
            }
        }
    }

    Box(
        modifier = modifier.height(itemHeight * visibleItemsCount),
        contentAlignment = Alignment.Center
    ) {
        // Selection indicator background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .background(
                    color = PrimaryLight2,
                    shape = RoundedCornerShape(12.dp)
                )
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = infiniteItems.size,
                key = { index -> index }
            ) { index ->
                val itemValue = infiniteItems[index]
                val centerIndex = listState.firstVisibleItemIndex + 2
                val isCurrent = index == centerIndex && !listState.isScrollInProgress

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight)
                        .clickable {
                            coroutineScope.launch {
                                val targetIndex = maxOf(0, index - 2) // Ensure non-negative
                                listState.animateScrollToItem(targetIndex)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = itemValue.toString().padStart(2, '0'),
                        style = if (isCurrent) {
                            MaterialTheme.typography.displaySmall
                        } else {
                            MaterialTheme.typography.headlineSmall
                        },
                        color = if (isCurrent) {
                            Color.Black
                        } else {
                            Color.Gray.copy(alpha = 0.4f)
                        },
                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun TwoDayBookingForm(
    day1Date: String?,
    day1Hour: String,
    day1Minute: String,
    day2Date: String?,
    day2Hour: String,
    day2Minute: String,
    onDay1DateClick: () -> Unit,
    onDay1TimeClick: () -> Unit,
    onDay2DateClick: () -> Unit,
    onDay2TimeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Jadwal Hari Pertama
        Text(
            text = "Jadwal Hari Pertama",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Pilih Tanggal
        Text(
            text = "Pilih Tanggal",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = day1Date ?: "",
            onValueChange = {},
            placeholder = {
                Text(
                    "Pilih tanggal untuk hari pertama",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.6f)
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDay1DateClick),
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledBorderColor = Color.Gray.copy(alpha = 0.3f),
                disabledPlaceholderColor = Color.Gray.copy(alpha = 0.6f)
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                    contentDescription = "Calendar",
                    tint = Primary
                )
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pilih Waktu
        Text(
            text = "Pilih Waktu",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDay1TimeClick)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = day1Hour.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = ":",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = day1Minute.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Warning message and time slots (only show after date is selected)
        if (day1Date != null) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFF3E0))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Jadwal di bawah sudah terpakai. Silakan pilih waktu berbeda",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFE65100),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Time slots
            TimeSlot(time = "04:00 - 07:00")
            Spacer(modifier = Modifier.height(8.dp))
            TimeSlot(time = "08:00 - 09:00")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Jadwal Hari Kedua
        Text(
            text = "Jadwal Hari Kedua",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Pilih Tanggal
        Text(
            text = "Pilih Tanggal",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = day2Date ?: "",
            onValueChange = {},
            placeholder = {
                Text(
                    "Pilih tanggal untuk hari kedua",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.6f)
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDay2DateClick),
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledBorderColor = Color.Gray.copy(alpha = 0.3f),
                disabledPlaceholderColor = Color.Gray.copy(alpha = 0.6f)
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                    contentDescription = "Calendar",
                    tint = Primary
                )
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pilih Waktu
        Text(
            text = "Pilih Waktu",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDay2TimeClick)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = day2Hour.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = ":",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = day2Minute.padStart(2, '0'),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Warning message and time slots (only show after date is selected)
        if (day2Date != null) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFF3E0))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Jadwal di bawah sudah terpakai. Silakan pilih waktu berbeda",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFE65100),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Time slots
            TimeSlot(time = "03:00 - 04:00")
        }
    }
}

@Composable
fun TimeSlot(
    time: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE0E0E0))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    currentMonth: Calendar,
    onMonthChange: (Calendar) -> Unit,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedDate by remember { mutableStateOf<Int?>(null) }
    var monthState by remember { mutableStateOf(currentMonth) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        title = null,
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Month Navigation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val newMonth = monthState.clone() as Calendar
                        newMonth.add(Calendar.MONTH, -1)
                        monthState = newMonth
                        onMonthChange(newMonth)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Previous Month",
                            tint = Primary
                        )
                    }

                    Text(
                        text = monthState.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("id", "ID"))
                            ?.replaceFirstChar { it.uppercase() } ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    IconButton(onClick = {
                        val newMonth = monthState.clone() as Calendar
                        newMonth.add(Calendar.MONTH, 1)
                        monthState = newMonth
                        onMonthChange(newMonth)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Next Month",
                            tint = Primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Day Headers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min").forEach { day ->
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Calendar Grid
                CalendarGrid(
                    calendar = monthState,
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    selectedDate?.let { day ->
                        val dateString = String.format(
                            "%02d-%02d-%04d",
                            day,
                            monthState.get(Calendar.MONTH) + 1,
                            monthState.get(Calendar.YEAR)
                        )
                        onDateSelected(dateString)
                    }
                },
                enabled = selectedDate != null,
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal", color = Primary)
            }
        }
    )
}

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    modifier: Modifier = Modifier
) {
    // Deprecated - use ScrollableNumberPicker instead
    ScrollableNumberPicker(value, onValueChange, range, modifier)
}

@Preview
@Composable
private fun Pemesanan2ScreenPreview() {
    RiasinTheme {
        Pemesanan2Screen()
    }
}
