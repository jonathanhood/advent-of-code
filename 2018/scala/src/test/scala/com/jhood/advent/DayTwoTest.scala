package com.jhood.advent

import org.jhood.advent.{DayTwoPartOne, DayTwoPartTwo}
import org.scalatest.FunSuite

class DayTwoTest extends FunSuite {
  test("part 1 - evaluate the example") {
    val ids = List(
      "abcdef",
      "bababc",
      "abbcde",
      "abcccd",
      "aabcdd",
      "abcdee",
      "ababab"
    )

    val evaluated = ids.map(DayTwoPartOne.evaluateId)

    assert(evaluated == List(
      (0,0),
      (1,1),
      (1,0),
      (0,1),
      (1,0),
      (1,0),
      (0,1)
    ))

    assert(DayTwoPartOne.checksum(evaluated) == 12)
  }

  test("part 1 - solve the test") {
    val ids = input.lines.filterNot(_.isEmpty).toList
    val evaluated = ids.map(DayTwoPartOne.evaluateId)
    val result = DayTwoPartOne.checksum(evaluated)
    println(s"Day 2 Part 1 - $result")
  }

  test("part 2 - evaluate the example") {
    val ids = List(
      "abcde",
      "fghij",
      "klmno",
      "pqrst",
      "fguij",
      "axcye",
      "wvxyz"
    )

    assert(DayTwoPartTwo.solve(ids) == "fgij")
  }

  test("part 2 - solve the test") {
    val ids = input.lines.filterNot(_.isEmpty).toList
    val result = DayTwoPartTwo.solve(ids)
    println(s"Day 2 Part 2 - $result")
  }

  val input =
    """
      |kbqwtcvzgumhpwelrnaxydpfuj
      |kbqwtcvzgsmhpoelryaxydiqij
      |kbqwpcvzssmhpoelgnaxydifuj
      |kbqgtcvxgsmhpoalrnaxydifuj
      |kbqwtcvygsmhpoelrnaxydiaut
      |kbqwtcvjgsmhpoelrnawydzfuj
      |kbqftcvzgsmhpoeprnaxydifus
      |rbqwtcgzgsxhpoelrnaxydifuj
      |kbqwtlvzgvmhpoelrnaxkdifuj
      |kbqwtcvzgsmhpolqrnaxydifub
      |kbqbtcqzgsmhposlrnaxydifuj
      |kbqwmcvzgswhpoelxnaxydifuj
      |kbqwtyvzgsmhkoelrnsxydifuj
      |khqwtcvzgsmhqoelinaxydifuj
      |koqwtcvzcsmhpoelrnaxydizuj
      |kbqwtcvzlsmhpoezrnaxydmfuj
      |kbqwtcvzdsmhpoelrjaxydifij
      |kbqwtcvzgsmhpoelrncxyjifuk
      |kbtwtcvzgsmhpoelonaxydiwuj
      |kbqwfcrzgsmhpoelrnaeydifuj
      |kbqutcvkgsmhpoelrnfxydifuj
      |kbqwtcvzgsmvvoelrnaxydihuj
      |kbqwtcvzhymhpoelrnaxydifyb
      |kbqctcvzgumhpoalrnaxydifuj
      |kuqktcvzgsmhpoelrnaxydieuj
      |kbqwtcvzgsmvpozlrnaxydifmj
      |kbqwtcvzgsmhpojlraaxydiouj
      |kbqwtcvzgmmhpoelknaxydizuj
      |kbwwtcvzgsmhpoefrnaxydifij
      |kbqwucvzgsmhpoelvnahydifuj
      |kbqwtcvzpsmhpgelrqaxydifuj
      |kblqtcvzgsmhpoeirnaxydifuj
      |kbqwtcvzgsmhpovlrnabydifum
      |kbqwwcvzgsmhpoelrnaoydnfuj
      |kyqwdcvzgsmhpoelrnaxfdifuj
      |kbqftcvzgsmxpoelknaxydifuj
      |kbqwtsvzksmhpoelqnaxydifuj
      |kbqwtcvzgsmhplelrnauydifux
      |kbqytcvzgsmhpkelrnaxydefuj
      |kbqwtcvzgsmjjoelrlaxydifuj
      |kbqvtcvzgsmhpoelnnaxydafuj
      |kbqwtcvzgsjhioelrnaxpdifuj
      |kbqptcvpgsmhpoelrnaxydiful
      |kbqwjcazgimhpoelrnaxydifuj
      |kbqxtcvzgwmhpaelrnaxydifuj
      |kbqwtcezgsmhqoelrnaxydifub
      |kbqwtcvzgsmhooelynaxydifuf
      |kbqwtwvzgsmkpoelrnaxrdifuj
      |nbqwtcvugsmhpoelrnzxydifuj
      |kbvwqcvzgsmhpoelsnaxydifuj
      |kbqwtcyzjsmhpoelrnaxymifuj
      |kbqwtcvzgsmhpoclrnaxykzfuj
      |kbbwtcvzgsmhyodlrnaxydifuj
      |kbwwtcvzgsmytoelrnaxydifuj
      |kbmwtcczgpmhpoelrnaxydifuj
      |ubqwtcvzgsmmpoblrnaxydifuj
      |kbqwtcvzgrmhpoelrnaxnrifuj
      |kbqwhcvzgsmhpoelynaaydifuj
      |kbqwtcvzgsmtpoelrcpxydifuj
      |kdqwtchzgsmhpoelrmaxydifuj
      |qbqrncvzgsmhpoelrnaxydifuj
      |kbqwtcvzghshpoelrnaxodifuj
      |kbqwhcvzgsmhpoelknaxydiwuj
      |ebqwtcvzgsmhpoelrotxydifuj
      |kbqwacvzusmhpoelryaxydifuj
      |kbqwtcvggsmhpoelrnaxygifyj
      |kbqwtcvzgsmhpoelrnaxycwfuo
      |kzqwzcvzgsmhpoelrxaxydifuj
      |khqwtcvzgsmhpoelrnaxldifyj
      |kbqwtbtzgsmhpoelrnaxydifud
      |gbqwtcvzgqmhpoelrnaxydifrj
      |kbqdtqvzgwmhpoelrnaxydifuj
      |kbqwscvzgsmhpoelrpaxypifuj
      |kmqwtcdzgsmhpoelenaxydifuj
      |klqwtcvvgsmhpoelrfaxydifuj
      |kbuwtcvzgsmhpoelrtaxyuifuj
      |kbqwtcvrgomhpoelrnaxydijuj
      |kbqwtgvzgsmhzoelrnpxydifuj
      |kbqltcvzgsmhooeljnaxydifuj
      |kbqwtcvzgbmxpoelrnaxydivuj
      |kbqdtcmzgsmhpoelrnaxydmfuj
      |kbqwtcazgsmhpoplrnacydifuj
      |kbqztcvegsmhpoelrnvxydifuj
      |kbqwtcvzgsmhpoecrnaxydzfsj
      |kbqwtcvzgsmepoelrnaqydifuf
      |kbqwtcqzgsmhpoelrnoxydivuj
      |kbqwtcvzgsmhpoeylnaxydhfuj
      |kbqwtcvfgsmhpoelrnaxgdifyj
      |kbqwtcvzgsmhnbelrnaxyfifuj
      |kbqwtcvzgsmhpoelrnaxbdffmj
      |kwqwtcvogtmhpoelrnaxydifuj
      |kdqwtcvzggyhpoelrnaxydifuj
      |kbqwtuvzgtmhpoelrnaxydifxj
      |kbqctdvzcsmhpoelrnaxydifuj
      |kbqwtcvzgsmhpoblrniyydifuj
      |kbqwucvzzsmhpoelrnvxydifuj
      |kbqwtcvzgslzpoelrnaxydiruj
      |kbqwtdmzgsmhpwelrnaxydifuj
      |kbqwtcvzgsmhpoilrnaxqiifuj
      |kbqwtcvzgsmhpgelrnaxydisnj
      |kbdwtqvzgsmhpoelrnaxydivuj
      |kbqvtdvzgsmhpoelrjaxydifuj
      |kfqwtcvzgsmhpoeurnyxydifuj
      |kbqwtcvzgsmhpoglrnaxqkifuj
      |kbqwtcvrgsmhpoelrnajydifnj
      |xbqwpcvzgjmhpoelrnaxydifuj
      |kbqwtcvzgsmhpoelrdaxvdihuj
      |kbuwtcvzssmhpoklrnaxydifuj
      |kbqwtcvzgqmhpoelrnzxydifbj
      |kbqwtcvzgsmhsoeoknaxydifuj
      |kfqltcvzgsmhpoelrnaxydifnj
      |qbqwtsvzgsmhpoelrnaxodifuj
      |kbqwwevzgsmypoelrnaxydifuj
      |kbqwtcuzgimhpoelrnaxydffuj
      |kxqwlcvzgsmhpoelrnaxyrifuj
      |nbqwtcvzgsmhpoelryaxyiifuj
      |kbqwtcvzgsmhhoxlreaxydifuj
      |mbqwtcvzfsmxpoelrnaxydifuj
      |kbqwttvzgsmhpoeqrnaxidifuj
      |kbqwtcvzgamhpielrnaxyiifuj
      |rfqwtcvzgsmhpoelrnaxydifun
      |kbpwtqvzgsmbpoelrnaxydifuj
      |kbqwtcvzgsmhpoqlroaxydifua
      |hbqwtcvzksmhpoelrnaxydbfuj
      |kaqutcvzgsmhpoelrnaxydiiuj
      |kbqctcvzgsnhpoelrcaxydifuj
      |kbqwtnvzgsmhpoelrnaxydqfoj
      |kbqwtcvzhsmhpoelrnaxydifyb
      |ubqwtcvcgsmhooelrnaxydifuj
      |kbqwtcvrgsmhpoelrnaxtdivuj
      |kbqwtcvzgsmhplelrnmxydifaj
      |ebqwlcvzghmhpoelrnaxydifuj
      |hbqwtcvzgsmhpoelrnaqyeifuj
      |kbqstcvzgsmeprelrnaxydifuj
      |kbqwtcvogsthpoelrnnxydifuj
      |ybqwtcvzgdmhpoelrnaxydufuj
      |kbqutcvzgsmhpoelrnaxydifgx
      |kbqwtcvzgsmhpozlunadydifuj
      |kkqwtcvzgsmhpuefrnaxydifuj
      |kbqrtcvzgsmhpoelrnaxcdifuq
      |kbqwtcvzjsmupoelrnaxydiluj
      |kbqwmcvzgsuhpoelrnaxydifhj
      |kbqwfcvzgsmhpoelrnaxydkzuj
      |kbqatcvzgsdhpoeyrnaxydifuj
      |kbtwtcvzusmhpoelrxaxydifuj
      |kbqwtcwzgsmhpoelrnaxysofuj
      |kbqqtcvmgsmhpoevrnaxydifuj
      |kbqwjcvzgsmhpoelrnaxydhuuj
      |mbdwtcvzgsmhpoelqnaxydifuj
      |kbqwtcvlgsmhpoelrdaxydifaj
      |kbqwtcvzgsmmpoelrlaxydnfuj
      |kbqwtchfggmhpoelrnaxydifuj
      |kbqqtcvzgsyhpoelrnaxyoifuj
      |knqwtcvzqsmupoelrnaxydifuj
      |kdqdtcvzgsmhpoelrnaxydmfuj
      |kbqwtcvzgsmhptelrnawyhifuj
      |kbqwtcvzgrmhpoeqrnaxydifuw
      |kbnxtcvzgsmhpoelrnauydifuj
      |kbqwacvsgsmhpoelrnaxydifgj
      |kbqwtcvzgsmhpperrnaxydifuc
      |gbqwtcvzgsqhxoelrnaxydifuj
      |kbqwtcvzgsmhpoeljgaxydifwj
      |kbqktcvzgsmhpotlrnatydifuj
      |bbqwtcvzgsmhpoilrnaxydjfuj
      |kbqwecvdgsmhpoelrnaxypifuj
      |keqwtcvzgemhpotlrnaxydifuj
      |kbqptcvzgsmvpoelrnaxydixuj
      |kbqwbctzgsmhpoelrnaxydifup
      |kbqwtcvzgszhpbelrnzxydifuj
      |mbqwtcvtgsmhpoeyrnaxydifuj
      |kbqwtcvzgsmhqcelrhaxydifuj
      |kbqotcvzgsmhooelrnazydifuj
      |kbqwtcvzgsmhpoelmpaxyiifuj
      |kbqwtcvwgsmypoclrnaxydifuj
      |kbqwtcvsgskhpoelrnaxykifuj
      |kbqwtcvzgszvpoelrnwxydifuj
      |kbqwtcvzgsmhpoejonaxydrfuj
      |kbqwtcvzgsmhkoelrnazyqifuj
      |kbzwtzvzgsmhptelrnaxydifuj
      |kbqwtcdzgsmhptelrnaxydiduj
      |kbqwtcvzgamhpoelrnakyzifuj
      |kbqwtcvzgsmhpoeonnaxydifxj
      |kbqwtcvzgsmhpoeranaxydifej
      |kbqwscvzgsmhpoelunaxydimuj
      |cbqwtcvzgsmhpoelrdaxydefuj
      |vbqwtcjzgsmhpoelrnaxydifua
      |kmqwtcvzksmhpoeljnaxydifuj
      |kbqwtcvzgsmppojlrnasydifuj
      |kaqwtcvfgsmhpoelrnaxydiauj
      |khqwccvzgsmhpoelrnaxydifud
      |vbqwtcvzrsmhpoelrhaxydifuj
      |kuqwtcvzgsmhpoelgnaiydifuj
      |kbqwtcvzdsmhpbelvnaxydifuj
      |kbowtcvzgnmhpoelrfaxydifuj
      |kbqwtcvsgsmhfoejrnaxydifuj
      |kbqwtcvzgskhtoelrnxxydifuj
      |kbqwtcvzgtmhpoevrnaxydivuj
      |bbqptcgzgsmhpoelrnaxydifuj
      |kbqwtpvzgsmnpoelhnaxydifuj
      |kbqwtovzgsmmpoelrnaxydifuw
      |kbqwtcvzgsihpwelrnaxydsfuj
      |kbqwtcvzggmhpollrnaxydifsj
      |kbqwtcjzgsmhpoelrnaxyxifub
      |ebqwtcvzgsmzpoelrnaaydifuj
      |kbqwtcvzusmhpoelrnqxydijuj
      |obqwtcvzgsghpoelrnaxydifkj
      |kbrwtcvzmdmhpoelrnaxydifuj
      |kbqwtcvzxsmhpoblrnhxydifuj
      |kbqwacvzgsahpoelrnaxydiguj
      |kyqwtcvzgsmipoelrnlxydifuj
      |kbbwtcvzgsmhboelpnaxydifuj
      |kbqwtcvzgsmhpoelrnaxhdosuj
      |kbqwtgvzgxmhpoelrnaxyrifuj
      |pbqwtsvzgsmhpoelrnabydifuj
      |kbqrtcvzgsmhpsblrnaxydifuj
      |kbqwtcvzgsmhpoexrnaaycifuj
      |kbqxtcvzgsjhkoelrnaxydifuj
      |kbqwtcvzgsmhpxelrnaxydifby
      |lbxwtcvzgsmdpoelrnaxydifuj
      |kbqwtcczgsmhpoklrnzxydifuj
      |zbqwtcvzgsmhpoelrbaxydifui
      |krqwtcvzbsmhpoelrjaxydifuj
      |kbkwtcvzgsmhpoelrnaxydiacj
      |kbqwtcvzgszhpseprnaxydifuj
      |kbxwtcvzxsmhpoesrnaxydifuj
      |kbqwdcvzgsmhpoelrbaxygifuj
      |kbqwthkzgsmhhoelrnaxydifuj
      |klqwtchzgamhpoelrnaxydifuj
      |obqwtcvzgsvcpoelrnaxydifuj
      |kblwtcvzgsmhpoelrnanydifuw
      |kbqwtrvzgsmhpoelynaxydifug
      |kbqwtcvzgsmhcoelmnaxydkfuj
      |kbqwtcvzgsmhpotlqoaxydifuj
      |kaqatcvzgsmhpoelrnaxyiifuj
      |kbqttcvwgsmhpoelrnaxydifgj
      |kpqwtcvzgsmhpwelynaxydifuj
      |kbqwucvzgsmhpyelrnaxyxifuj
      |kbqwucvzgsmhprelrnaxyfifuj
      |kbqwthvzgsmhphelrnaxylifuj
      |kbqwtcvzosmhdoelrnaxwdifuj
      |kbqwtxvsgsphpoelrnaxydifuj
      |koqwtcvfghmhpoelrnaxydifuj
      |kbtwicvzpsmhpoelrnaxydifuj
      |kbawtcvzgsmhmoelrnaxyiifuj
      |kbqwtcvzgslhpbelrnaxydifuk
      |kbqttcvzgsmypoelrnaxydifua
      |kbqwtcvrgqmhpnelrnaxydifuj
      |kbqwtcvzghmhpoekpnaxydifuj
      |kbqwtcvzgsmupoelrnaxidifui
      |kbqwtcvzgsmhpbelrnaxrdifux
    """.stripMargin
}
