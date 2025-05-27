package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.actions.ChooseDiscardAction;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
import static com.megacrit.cardcrawl.core.CardCrawlGame.sound;

public class Zhiheng extends CustomCard {
    public static final String ID = "Liuyanmod:Zhiheng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Zhiheng_skill.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int DRAW_BASE = 0;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public Zhiheng() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, EXAMPLE_GREEN, RARITY, TARGET);
        this.baseMagicNumber = DRAW_BASE;
        this.magicNumber = DRAW_BASE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        sound.play("ZHIHENG");
        int handSize = p.hand.group.size();
        if (handSize > 0) {
            addToBot(new ChooseDiscardAction(handSize, this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Zhiheng();
    }
}