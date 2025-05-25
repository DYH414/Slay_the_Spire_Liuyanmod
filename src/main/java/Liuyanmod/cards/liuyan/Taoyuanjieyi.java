package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Taoyuanjieyi extends CustomCard {
    public static final String ID = "Liuyanmod:Taoyuanjieyi";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Taoyuanjieyi_skill.png";

    private static final int COST = 1;
    private static final int BASE_ENERGY_PER_ENEMY = 1;
    private static final int UPGRADE_PLUS_ENERGY = 1;

    public Taoyuanjieyi() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = BASE_ENERGY_PER_ENEMY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int aliveEnemies = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                aliveEnemies++;
            }
        }
        if (aliveEnemies > 0) {
            addToBot(new GainEnergyAction(aliveEnemies * this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ENERGY);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; // 🔥关键！换成升级文本
            initializeDescription();
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Taoyuanjieyi();
    }
}
