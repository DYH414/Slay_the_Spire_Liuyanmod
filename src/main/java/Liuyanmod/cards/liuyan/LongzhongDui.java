package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class LongzhongDui extends CustomCard {
    public static final String ID = "Liuyanmod:LongzhongDui";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Longzhongdui_skill.jpg";
    private static final CardType TYPE = CardType.STATUS; // 状态牌
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2; // -2表示不可打出

    public LongzhongDui() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isEthereal = true; // 回合结束如果在手里就消失
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToTop(new GainEnergyAction(1)); // 抽到时获得1点能量
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 不能用，不需要写任何效果
    }

    @Override
    public void upgrade() {
        // 状态牌不能升级
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongzhongDui();
    }
}
