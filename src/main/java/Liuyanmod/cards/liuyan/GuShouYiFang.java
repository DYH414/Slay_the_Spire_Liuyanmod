package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class GuShouYiFang extends CustomCard {
    public static final String ID = "Liuyanmod:GuShouYiFang";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Gushouyifang_skill.png"; // 图片路径
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON; // 普通卡
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int BLOCK = 13; // 格挡值
    private static final int GEJU_AMOUNT = 1; // 割据层数

    public GuShouYiFang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, EXAMPLE_GREEN, RARITY, TARGET);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        addToBot(new GainBlockAction(p, p, this.block));
        // 获得割据能力
        addToBot(new ApplyPowerAction(p, p, new GejuPower(p, GEJU_AMOUNT), GEJU_AMOUNT));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3); // 升级后格挡值 +3
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; // 更新描述
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuShouYiFang();
    }
}
