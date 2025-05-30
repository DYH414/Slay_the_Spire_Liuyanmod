package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.JiedongfengPower;
import Liuyanmod.powers.ZhuoshaoPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Jiedongfeng extends CustomCard {
    public static final String ID = "Liuyanmod:Jiedongfeng";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "Liuyan/img/cards/Jiedongfeng_power.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Jiedongfeng() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true; // 消耗
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 给玩家施加借东风能力
        this.addToBot(new ApplyPowerAction(p, p, new JiedongfengPower(p)));

        // 同时将已存在的所有灼烧效果转为永久
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster != null && !monster.isDeadOrEscaped() && monster.hasPower(ZhuoshaoPower.POWER_ID)) {
                ZhuoshaoPower zhuoshao = (ZhuoshaoPower) monster.getPower(ZhuoshaoPower.POWER_ID);
                if (zhuoshao != null && !zhuoshao.isPermanent) {
                    zhuoshao.makePermanent();
                    zhuoshao.flash(); // 闪烁效果提示玩家
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0); // 升级后费用降为0
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jiedongfeng();
    }
}