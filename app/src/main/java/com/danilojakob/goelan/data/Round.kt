package com.danilojakob.goelan.data

import com.danilojakob.goelan.util.RoundType

data class Round(var type: RoundType, var question: Question?, var miniGame: MiniGame?)
