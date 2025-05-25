package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import Liuyanmod.powers.ZhuLuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ZhuLuTianXia extends CustomCard {
    public static final String ID = "Liuyanmod:ZhuLuTianXia";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Zhulutianxia_power.png"; // 图片路径
    private static final CardType TYPE = CardType.POWER; // 能力卡
    private static final CardRarity RARITY = CardRarity.RARE; // 金色稀有卡
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = EXAMPLE_GREEN;

    public ZhuLuTianXia() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1; // 基础摸牌数量
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得“逐鹿”能力
        this.addToBot(new ApplyPowerAction(p, p, new ZhuLuPower(p, this.magicNumber)));
        // 获得 1 层“割据”能力
        this.addToBot(new ApplyPowerAction(p, p, new GejuPower(p, 1)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; // 更新描述
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ZhuLuTianXia();
    }
}
