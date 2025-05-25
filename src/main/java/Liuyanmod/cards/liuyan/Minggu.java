package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Minggu extends CustomCard {
    public static final String ID = "Liuyanmod:Minggu";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Minggu_skill.jpg"; // 请替换为你的图片路径

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = EXAMPLE_GREEN;

    private static final int COST = 1;
    private static final int BASE_BLOCK = 6;
    private static final int BONUS_PER_CARD = 1;
    private static final int UPGRADE_BONUS = 1;

    public Minggu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BASE_BLOCK;
        this.magicNumber = this.baseMagicNumber = BONUS_PER_CARD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int totalBlock = this.baseBlock + p.hand.size() * this.magicNumber;
        this.addToBot(new GainBlockAction(p, p, totalBlock));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_BONUS); // 升级后每张卡+2格挡
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Minggu();
    }
}
