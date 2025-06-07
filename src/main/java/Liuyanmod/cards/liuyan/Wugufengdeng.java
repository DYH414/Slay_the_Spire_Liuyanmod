package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Wugufengdeng extends CustomCard {
    public static final String ID = "Liuyanmod:Wugufengdeng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG_PATH = "Liuyan/img/cards/Wugufengdeng_skill.png";
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Wugufengdeng() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, EXAMPLE_GREEN, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 统计当前房间中所有存活（未死亡或逃跑）的怪物数量
        int alivecnt=0;
        for(AbstractMonster mo:AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            if(!mo.isDeadOrEscaped())
            {
                alivecnt++;
            }
        }
        // 抽牌：存活怪物数量 + magicNumber（默认为1）
        addToBot(new DrawCardAction(p,alivecnt*this.magicNumber+1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            // 升级后多抽1张
            this.upgradeBaseCost(0);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wugufengdeng();
    }
}
