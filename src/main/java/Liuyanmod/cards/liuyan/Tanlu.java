package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.actions.TanluEnergyCheckAction;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Tanlu extends CustomCard {
    public static final String ID = "Liuyanmod:Tanlu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Tanlu_skill.jpg";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    private static final int BASE_DRAW = 2;
    private static final int UPGRADE_DRAW = 1;

    public Tanlu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = BASE_DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 抽卡 + 检查攻击牌数目回复能量
        this.addToBot(new DrawCardAction(this.magicNumber, new TanluEnergyCheckAction()));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_DRAW); // 抽牌数 +1
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tanlu();
    }
}
