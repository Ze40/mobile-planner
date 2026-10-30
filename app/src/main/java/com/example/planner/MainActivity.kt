package com.example.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.planner.ui.day.DayPlanViewModel
import com.example.planner.ui.navigation.PlannerRoot
import com.example.planner.ui.theme.PlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dayPlanViewModel = ViewModelProvider(this)[DayPlanViewModel::class.java]
        setContent {
            PlannerTheme {
                PlannerRoot(viewModel = dayPlanViewModel)
            }
        }
    }
}
