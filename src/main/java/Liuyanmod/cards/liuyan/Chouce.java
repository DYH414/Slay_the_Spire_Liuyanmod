package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Chouce extends CustomCard {
    public static final String ID = "Liuyanmod:Chouce";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/testimg/Test_skill.png";

    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int BLOCK = 16;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public Chouce() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        this.addToBot(new GainBlockAction(p, p, this.block));
        System.out.println("Chouce used, gained " + this.block + " block");

        // 检查立牧效果
        if (p.hasPower("Liuyanmod:LimuPower")) {
            // 抽牌
            this.addToBot(new DrawCardAction(p, this.magicNumber));
            // 下一张技能卡费用为 0
            this.addToBot(new ApplyPowerAction(p, p, new Liuyanmod.powers.ChoucePower(p, 1), 1));
            System.out.println("Chouce triggered Limu effect: draw " + this.magicNumber + ", next skill card cost 0");
        }

        // 施加立牧
        this.addToBot(new ApplyPowerAction(p, p, new Liuyanmod.powers.LimuPower(p), 1));
        System.out.println("Chouce applied 1 layer of LimuPower");
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chouce();
    }
}