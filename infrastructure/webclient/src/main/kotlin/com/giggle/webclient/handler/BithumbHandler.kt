package com.giggle.com.giggle.websocket.handler

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.domain.bithumb.request.BithumbSocket
import com.giggle.domain.bithumb.response.BithumbTickerResponse
import com.giggle.com.giggle.websocket.log.Logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*

@Component
class BithumbHandler(
    private val kafkaTemplate: KafkaTemplate<String, String>
): TextWebSocketHandler() {
    private val logger = Logger()
    private var responseCheck = false

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val response =
            BithumbSocket.createTickerRequest(
                //TODO: symbol -> db에서 관리하기
                listOf("BTC_KRW", "ETH_KRW", "ETC_KRW", "XRP_KRW", "BCH_KRW", "QTUM_KRW", "BTG_KRW", "EOS_KRW", "ICX_KRW", "TRX_KRW", "ELF_KRW", "KNC_KRW", "GLM_KRW", "ZIL_KRW", "WAXP_KRW", "POWR_KRW", "LRC_KRW", "STEEM_KRW", "STRAX_KRW", "ZRX_KRW", "SNT_KRW", "ADA_KRW", "CTXC_KRW", "BAT_KRW", "THETA_KRW", "LOOM_KRW", "CVC_KRW", "WAVES_KRW", "LINK_KRW", "ENJ_KRW", "VET_KRW", "MTL_KRW", "IOST_KRW", "AMO_KRW", "BSV_KRW", "ORBS_KRW", "TFUEL_KRW", "VALOR_KRW", "CON_KRW", "ANKR_KRW", "MIX_KRW", "CRO_KRW", "FX_KRW", "CHR_KRW", "MBL_KRW", "MXC_KRW", "FCT2_KRW", "WOM_KRW", "BOA_KRW", "MEV_KRW", "SXP_KRW", "COS_KRW", "EL_KRW", "HIVE_KRW", "XPR_KRW", "VRA_KRW", "FIT_KRW", "EGG_KRW", "BORA_KRW", "ARPA_KRW", "CTC_KRW", "APM_KRW", "CKB_KRW", "AERGO_KRW", "EVZ_KRW", "QTCON_KRW", "UNI_KRW", "YFI_KRW", "UMA_KRW", "AAVE_KRW", "COMP_KRW", "BAL_KRW", "RSR_KRW", "NMR_KRW", "RLC_KRW", "UOS_KRW", "SAND_KRW", "STPT_KRW", "BEL_KRW", "OBSR_KRW", "ORC_KRW", "POLA_KRW", "ADP_KRW", "DVI_KRW", "GHX_KRW", "MVC_KRW", "BLY_KRW", "GRT_KRW", "BIOT_KRW", "SNX_KRW", "SOFI_KRW", "GRACY_KRW", "OXT_KRW", "MAP_KRW", "AQT_KRW", "WIKEN_KRW", "CTSI_KRW", "MANA_KRW", "LPT_KRW", "MKR_KRW", "SUSHI_KRW", "ASM_KRW", "PUNDIX_KRW", "CELR_KRW", "FRONT_KRW", "RLY_KRW", "OCEAN_KRW", "BFC_KRW", "ALICE_KRW", "OGN_KRW", "COTI_KRW", "CAKE_KRW", "BNT_KRW", "XVS_KRW", "SWAP_KRW", "CHZ_KRW", "AXS_KRW", "DAO_KRW", "SIX_KRW", "DAI_KRW", "SHIB_KRW", "MATIC_KRW", "WOO_KRW", "ACH_KRW", "VELO_KRW", "XLM_KRW", "ONT_KRW", "META_KRW", "KLAY_KRW", "ONG_KRW", "ALGO_KRW", "JST_KRW", "XTZ_KRW", "MLK_KRW", "DOT_KRW", "ATOM_KRW", "TEMCO_KRW", "DOGE_KRW", "KSM_KRW", "CTK_KRW", "BNB_KRW", "NFT_KRW", "SUN_KRW", "XEC_KRW", "AGIX_KRW", "SOL_KRW", "FNSA_KRW", "EGLD_KRW", "MASK_KRW", "C98_KRW", "MED_KRW", "1INCH_KRW", "CRV_KRW", "BOBA_KRW", "DYDX_KRW", "MINA_KRW", "FLOW_KRW", "JOE_KRW", "GALA_KRW", "BTT_KRW", "JASMY_KRW", "REQ_KRW", "CSPR_KRW", "AVAX_KRW", "TDROP_KRW", "HBAR_KRW", "FANC_KRW", "NPT_KRW", "REI_KRW", "T_KRW", "MBX_KRW", "GMT_KRW", "TAVA_KRW", "DAR_KRW", "APE_KRW", "WNCG_KRW", "ALT_KRW", "XCN_KRW", "AZIT_KRW", "FLR_KRW", "SFP_KRW", "FITFI_KRW", "STAT_KRW", "CRTS_KRW", "VIX_KRW", "LBL_KRW", "FLZ_KRW", "LM_KRW", "GRND_KRW", "APT_KRW", "BLUR_KRW", "WEMIX_KRW", "OAS_KRW", "HOOK_KRW", "ENTC_KRW", "ONIT_KRW", "OP_KRW", "ROA_KRW", "GMX_KRW", "STX_KRW", "XPLA_KRW", "ARB_KRW", "INJ_KRW", "HFT_KRW", "RPL_KRW", "IMX_KRW", "CFX_KRW", "ACS_KRW", "FXS_KRW", "CELO_KRW", "LDO_KRW", "FTM_KRW", "FET_KRW", "SUI_KRW", "NCT_KRW", "FLOKI_KRW", "ALEX_KRW", "ID_KRW", "RNDR_KRW", "STG_KRW", "OSMO_KRW", "GAL_KRW", "ILV_KRW", "MAV_KRW", "RSS3_KRW", "AUDIO_KRW", "AGI_KRW", "RDNT_KRW", "ASTR_KRW", "WLD_KRW", "FLUX_KRW", "RVN_KRW", "LEVER_KRW", "EDU_KRW", "SEI_KRW", "WAXL_KRW", "MOC_KRW", "PEPE_KRW", "CYBER_KRW", "ARKM_KRW", "PYR_KRW", "IOTX_KRW", "HIGH_KRW", "PENDLE_KRW", "STORJ_KRW", "API3_KRW", "ZTX_KRW", "MNT_KRW", "GTC_KRW", "TIA_KRW", "ZBC_KRW", "SPURS_KRW", "NEO_KRW", "GAS_KRW", "HIFI_KRW", "BIGTIME_KRW", "ARK_KRW", "YGG_KRW", "HUNT_KRW", "KAVA_KRW", "STMX_KRW", "MAGIC_KRW", "USDT_KRW", "USDC_KRW", "RAD_KRW", "LSK_KRW", "TT_KRW", "ACE_KRW", "PYTH_KRW", "MANTA_KRW", "JUP_KRW", "STRK_KRW", "PDA_KRW")
            )

        val subscribeMessage =
            jacksonObjectMapper().writeValueAsString(response)

        session.sendMessage(TextMessage(subscribeMessage))
    }

    /*
    * FIXME:
    *  Bithumb WebSocket api 사용 시
    *  잘못된 파라미터를 보내도 연결이 성공하고, 연결 성공 응답 값도 수신 되지만
    *  잘못된 파라미터를 받았다는 에러 메세지를 따로 응답해주지 않음.
    *  연결 성공 응답값을 받고 5초안에 코인 관련된 응답 값이 없다면 세션 종료하는 로직 추가.
    *
    * */
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val responseTree = jacksonObjectMapper().readTree(message.payload)

        when (responseTree.has("status")) {
            true -> extracted(session) //응답 데이터에 status 속성이 있다면 5초동안 응답 수신 체크
            false -> {
                try {
                    responseCheck = true
                    kafkaTemplate.send("bithumb", message.payload)
                    val ticker: BithumbTickerResponse = jacksonObjectMapper().readValue(message.payload)
                    logger.info("bithumb -> ${ticker.content.closePrice} ${ticker.content.symbol}")

                } catch (e: JsonMappingException) {
                    logger.error(e.message)
                }
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        //TODO: 세션 종료 후 로직
    }

    private fun extracted(session: WebSocketSession) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (!responseCheck) {
                    timer.cancel()
                    session.close()
                    logger.error("No response from Bithumb WebSocket Server")
                } else {
                    timer.cancel()
                }
            }
        }, 5000) // 소켓 최초 연결 후 5초안에 코인 관련 응답 값이 없다면 클라이언트 세션 종료
    }
}