package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Kongcheng extends CustomCard {
    public static final String ID = "Liuyanmod:Kongcheng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "Liuyan/img/cards/Kongcheng_skill.png";

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_PLUS_BLOCK = 3; // 升级后变成10
    private static final int BONUS_BLOCK = 5;

    public Kongcheng() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,
                EXAMPLE_GREEN, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得基础格挡
        CardCrawlGame.sound.play("KONGCHENG",true);
        this.addToBot(new GainBlockAction(p, p, this.block));
        // 若手牌中没有攻击牌，额外获得 BONUS_BLOCK 点格挡
        boolean hasAttack = false;
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.ATTACK) {
                hasAttack = true;
                break;
            }
        }
        if (!hasAttack) {
            this.addToBot(new GainBlockAction(p, p, BONUS_BLOCK));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Kongcheng();
    }
}