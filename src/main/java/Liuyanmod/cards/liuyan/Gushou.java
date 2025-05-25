package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Gushou extends CustomCard {
    public static final String ID = "Liuyanmod:Gushou";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "Liuyan/img/cards/Gushou_skill.jpg";

    private static final int COST = 0;
    private static final int BASE_BLOCK = 4;
    private static final int BASE_BLOCK_UPGRADED = 7;

    private static final int BONUS_BLOCK = 8;
    private static final int BONUS_BLOCK_UPGRADED = 11;

    public Gushou() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BASE_BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int blockGain = this.block;

        if (p.hasPower("Liuyanmod:LimuPower")) {
            blockGain += this.upgraded ? BONUS_BLOCK_UPGRADED : BONUS_BLOCK;
        }

        addToBot(new GainBlockAction(p, blockGain));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(BASE_BLOCK_UPGRADED - BASE_BLOCK);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public CustomCard makeCopy() {
        return new Gushou();
    }
}
