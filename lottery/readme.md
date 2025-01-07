# 待开发

* 

# 开发

2025-1-7 14:10:23

1. 抽卡逻辑改为根据遗物判断抽取卡的颜色。比如 有 ColorlessCards 遗物 则会加入无色卡。初始的卡包为自身角色的卡包
2. 三连奖励遗物获取优化。对于已经拥有的遗物过1/已有相同遗物数量的概率再次获取。对于中途获取的遗物存在 战斗开始时触发的效果时仍然触发
3. 优化遗物、卡片的说明。补充关键字
4. 开发完成三连奖励的遗物，加入一个白名单，不让一些遗物获得。
5. 开发完成必出三连器


2025-1-6 21:03:11

1. 修复抽卡又能抽到抽卡卡问题
2. new NewQueueCardAction来使用卡片存在问题，不知道什么问题，现在应该改为PlayTopCardAction（自定义） 改为了 [PlayCardAction.java](src%2Fmain%2Fjava%2Flottery%2Factions%2FPlayCardAction.java)
3. 升级卡也能合成，合成后给一个升级三连奖励+

# 说明

## 遗物

### 三连

开具随机生成3个抽卡包，然后进行抽取卡片。手牌上限+,5，三张一样的（等级）可以合成一张升级的卡片，并获得一张三连奖励。如果已经升级将不在合成

三连奖励：

### 必出三连器

todo 待开发

当达到20抽时，给一张三连

## 卡片

三连奖励：开具随机生成3个抽卡包，然后进行抽取卡片。对战开始时额外抽2张。如果手牌有三个未升级的相同卡片则合成三连(获得一张升级卡，以及一张奖励)。

三连奖励与遗物对应关系

STARTER（BASIC）

COMMON（COMMON）

UNCOMMON（UNCOMMON）

RARE（RARE）

SPECIAL（SPECIAL）

SHOP（CURSE）

普通卡：抽1次BASIC：35%基础卡（BASIC）、35%常见卡（COMMON）、10%不常见卡（UNCOMMON）、10%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

普通卡：抽5次COMMON：35%基础卡（BASIC）、35%常见卡（COMMON）、10%不常见卡（UNCOMMON）、10%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

普通卡：抽10次UNCOMMON：35%基础卡（BASIC）、35%常见卡（COMMON）、10%不常见卡（UNCOMMON）、10%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

好运卡：抽1次UNCOMMON：30%基础卡（BASIC）、30%常见卡（COMMON）、15%不常见卡（UNCOMMON）、15%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

好运卡：抽5次RARE：30%基础卡（BASIC）、30%常见卡（COMMON）、15%不常见卡（UNCOMMON）、15%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

好运卡：抽10次RARE：30%基础卡（BASIC）、30%常见卡（COMMON）、15%不常见卡（UNCOMMON）、15%稀有（RARE）、5%特殊（SPECIAL）、5%诅咒（CURSE）。消耗

超级好运卡：抽1次RARE：20%基础卡（BASIC）、20%常见卡（COMMON）、20%不常见卡（UNCOMMON）、20%稀有（RARE）、10%特殊（SPECIAL）、10%诅咒（CURSE）。消耗

超级好运卡：抽5次SPECIAL：20%基础卡（BASIC）、20%常见卡（COMMON）、20%不常见卡（UNCOMMON）、20%稀有（RARE）、10%特殊（SPECIAL）、10%诅咒（CURSE）。消耗

倒霉卡：抽1次COMMON：5%基础卡（BASIC）、5%常见卡（COMMON）、5%不常见卡（UNCOMMON）、5%稀有（RARE）、5%特殊（SPECIAL）、75%诅咒（CURSE）。消耗

倒霉卡：抽5次COMMON：5%基础卡（BASIC）、5%常见卡（COMMON）、5%不常见卡（UNCOMMON）、5%稀有（RARE）、5%特殊（SPECIAL）、75%诅咒（CURSE）。消耗
