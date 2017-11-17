package br.com.concrete.themoviebd

// Instance States
const val STATE_MACHINE = "STATE_MACHINE"
const val STATE_ADAPTER = "STATE_ADAPTER"
const val STATE_MACHINE_CURRENT_KEY = "StateMachine.CurrentKey"

// State Machine States
const val STATE_LOADING: Int = 0
const val STATE_SUCCESS: Int = 1
const val STATE_ERROR: Int = 2
const val STATE_EMPTY: Int = 3

// Recycler View Types
const val TYPE_ITEM = 1
const val TYPE_SEE_MORE = 2
