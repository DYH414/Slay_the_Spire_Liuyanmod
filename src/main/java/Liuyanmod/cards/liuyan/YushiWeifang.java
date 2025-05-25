package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class YushiWeifang extends CustomCard {
    public static final String ID = "Liuyanmod:YushiWeifang";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Yushiweifang_skill.jpg";

    private static final int COST = 0;
    private static final int BASE_BLOCK = 5;
    private static final int UPGRADE_BLOCK = 2;

    public YushiWeifang() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.SKILL,
                EXAMPLE_GREEN,
                CardRarity.COMMON,
                CardTarget.SELF);

        this.baseMagicNumber = BASE_BLOCK;
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 计算格挡层数
        int gejuStacks = p.hasPower(GejuPower.POWER_ID) ?
                p.getPower(GejuPower.POWER_ID).amount : 0;

        int totalBlock = gejuStacks * this.magicNumber;

        if (totalBlock > 0) {
            addToBot(new GainBlockAction(p, p, totalBlock));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BLOCK); // 5 → 7
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YushiWeifang();
    }
}
