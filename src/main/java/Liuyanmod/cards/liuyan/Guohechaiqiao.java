package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Guohechaiqiao extends CustomCard {
    public static final String ID = "Liuyanmod:Guohechaiqiao";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "Liuyan/img/cards/Guohechaiqiao_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int COST = 1;

    public Guohechaiqiao() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, EXAMPLE_GREEN, CardRarity.UNCOMMON,
                CardTarget.ENEMY);
        this.exhaust = true; // 使用后消耗
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && !m.isDeadOrEscaped()) {
            // 创建 powers 的副本以避免在迭代过程中修改原集合
            java.util.ArrayList<AbstractPower> powersCopy = new java.util.ArrayList<>(m.powers);

            // 如果敌人有任何能力（buff或debuff）
            if (!powersCopy.isEmpty()) {
                // 随机选择一个能力移除
                AbstractPower randomPower = powersCopy.get(new java.util.Random().nextInt(powersCopy.size()));
                addToBot(new RemoveSpecificPowerAction(m, p, randomPower));
                System.out.println("过河拆桥移除了敌人的能力: " + randomPower.name);
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public CustomCard makeCopy() {
        return new Guohechaiqiao();
    }
}